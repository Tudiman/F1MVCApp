package uniWork.f1app.Services;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uniWork.f1app.Entities.Race;
import uniWork.f1app.Exceptions.RaceNotFoundException;
import uniWork.f1app.Repositories.RaceRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RaceService {

    private final RaceRepository repository;
    private final static List<Integer> scoring = Arrays.asList(25,18,15,12,10,8,6,4,2,1);

    public List<String> all() {
        return repository.findAll().stream().map(Race::toString).collect(Collectors.toList());
    }

    public String findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RaceNotFoundException(id))
                .toString();
    }

    public List<Race> raw() {
        return repository.findAll();
    }

    public List<Pair<String, Integer>> getSeasonRankings(Integer year) {
        List<Race> races = repository.findByYear(year);
        Map<String, Integer> rankings = new HashMap<>();
        for(Race race:races) {
            for (int i = 0; i < race.getRankings().size(); i++) {
                String driver = race.getRankings().get(i);
                if (!rankings.containsKey(driver))
                    rankings.put(driver, scoring.get(i));
                else rankings.put(driver, rankings.get(driver) + scoring.get(i));
            }
        }
        Map<String, Integer> sorted =
                rankings.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<Pair<String, Integer>> finalResult = new ArrayList<>();
        for (Map.Entry<String,Integer> entry : sorted.entrySet())
            finalResult.add(new Pair<>(entry.getKey(), entry.getValue()));
        return finalResult;
    }

    public Race save(Race race) {
        return repository.save(race);
    }

    public String update(Race updatedRace, String id) {
        return repository.findById(id)
                .map(race -> {
                    race.setName(updatedRace.getName());
                    race.setYear(updatedRace.getYear());
                    race.setTrack(updatedRace.getTrack());
                    race.setRankings(updatedRace.getRankings());
                    return repository.save(race);
                }).orElseThrow(() -> new RaceNotFoundException(id))
                .toString();
    }

    public void delete(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw(new RaceNotFoundException(id));
    }
}
