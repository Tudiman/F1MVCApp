package uniWork.f1app.Services;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uniWork.f1app.Entities.Championship;
import uniWork.f1app.Entities.Driver;
import uniWork.f1app.Exceptions.ChampionshipNotFoundException;
import uniWork.f1app.Repositories.ChampionshipRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChampionshipService {

    private final ChampionshipRepository repository;
    private final RaceService raceService;

    public List<String> all() {
        return repository.findAll().stream().map(Championship::toString).collect(Collectors.toList());
    }

    public String findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChampionshipNotFoundException(id))
                .toString();
    }

    public Championship findByYear(Integer year) {
        return repository.findByYear(year);
    }

    public List<Championship> raw() {
        return repository.findAll();
    }

    public List<String> champions() {
        List<Championship> championships = raw();
        List<String> champions = new ArrayList<>();
        for(Championship championship: championships) {
            List<Pair<String, Integer>> rankings = raceService.getSeasonRankings(championship.getYear());
            if(rankings.size() > 0)
                champions.add("Winner of the " + championship.toString() + " is " + rankings.get(0).getKey() + " with " +
                        rankings.get(0).getValue() + " points!");
            else champions.add("Sadly, nobody scored any points in the " + championship.toString());
        }
        return champions;
    }

    public String champion(Integer year) {
        List<Pair<String, Integer>> rankings = raceService.getSeasonRankings(year);
        Championship championship = repository.findByYear(year);
        if(championship == null)
            return "There was no championship held in the year " + year;
        if(rankings.size() > 0)
             return "Winner of the " + championship.toString() + " is " + rankings.get(0).getKey() + " with " +
                    rankings.get(0).getValue() + " points!";
        else return "Sadly, nobody scored any points in the " + championship.toString();
    }

    public String mostTitles() {
        Map<String, Integer> map = new HashMap<>();
        List<Championship> championships = raw();
        for(Championship championship: championships) {
            List<Pair<String, Integer>> rankings = raceService.getSeasonRankings(championship.getYear());
            if(rankings.size() > 0) {
                String champion = rankings.get(0).getKey();
                if (!map.containsKey(champion))
                    map.put(champion, 1);
                else map.put(champion, map.get(champion) + 1);
            }
        }
        Map.Entry<String, Integer> winner = Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue));
        return winner.getKey() + " with " + winner.getValue() + " titles";
    }

    public Championship save(Championship championship) {
        return repository.save(championship);
    }

    public String update(Championship updatedChampionship, String id) {
        return repository.findById(id)
                .map(championship -> {
                    championship.setYear(updatedChampionship.getYear());
                    return repository.save(championship);
                }).orElseThrow(() -> new ChampionshipNotFoundException(id))
                .toString();
    }

    public void delete(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw(new ChampionshipNotFoundException(id));
    }
}

