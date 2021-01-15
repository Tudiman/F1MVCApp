package uniWork.f1app.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uniWork.f1app.Entities.Championship;
import uniWork.f1app.Entities.ChampionshipRegistration;
import uniWork.f1app.Entities.ChampionshipRegistrationTO;
import uniWork.f1app.Entities.Team;
import uniWork.f1app.Exceptions.ChampionshipRegistrationNotFoundException;
import uniWork.f1app.Repositories.ChampionshipRegistrationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChampionshipRegistrationService {

    private final ChampionshipRegistrationRepository repository;
    private final TeamService teamService;
    private final ChampionshipService championshipService;

    public List<String> all() {
        return repository.findAll().stream().map(ChampionshipRegistration::toString).collect(Collectors.toList());
    }

    public String findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChampionshipRegistrationNotFoundException(id))
                .toString();
    }

    public List<ChampionshipRegistration> raw() {
        return repository.findAll();
    }

    public ChampionshipRegistration findRawById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChampionshipRegistrationNotFoundException(id));
    }

    public List<Team> findTeamsByYear(Integer year) {
        return repository.findByChampionshipYear(year).stream()
                .map(ChampionshipRegistration::getTeam)
                .collect(Collectors.toList());
    }

    private ChampionshipRegistration generateChampionshipRegistration(ChampionshipRegistrationTO championshipRegistrationTO) {
        Team team = teamService.findByName(championshipRegistrationTO.getTeamName());
        if(team == null)
            return null;
        Championship championship = championshipService.findByYear(championshipRegistrationTO.getChampionshipYear());
        if(championship == null)
            return null;
        return new ChampionshipRegistration(team, championship);
    }

    public String save(ChampionshipRegistration championshipRegistration) {
        if(repository.findByTeamIdAndChampionshipYear(
                championshipRegistration.getTeam().getId(),
                championshipRegistration.getChampionship().getYear())
                .size() > 0)
            return "Team cannot be registered multiple times for same championship";
        repository.save(championshipRegistration);
        return repository.save(championshipRegistration) + " was saved";
    }

    public String save(ChampionshipRegistrationTO championshipRegistrationTO) {
        ChampionshipRegistration championshipRegistration =
                generateChampionshipRegistration(championshipRegistrationTO);
        if(championshipRegistration == null)
            return "Team or championship not found";
        return save(championshipRegistration);
    }

    public String update(ChampionshipRegistration updatedChampionshipRegistration, String id) {
        if(repository.findByTeamIdAndChampionshipYear(
                updatedChampionshipRegistration.getTeam().getId(),
                updatedChampionshipRegistration.getChampionship().getYear())
                .size() > 0)
            return "Team cannot be registered multiple times for same championship";
        return repository.findById(id)
                .map(championshipRegistration -> {
                    championshipRegistration.setTeam(updatedChampionshipRegistration.getTeam());
                    championshipRegistration.setChampionship(updatedChampionshipRegistration.getChampionship());
                    return repository.save(championshipRegistration);
                }).orElseThrow(() -> new ChampionshipRegistrationNotFoundException(id))
                .toString();
    }

    public String update(ChampionshipRegistrationTO championshipRegistrationTO, String id) {
        ChampionshipRegistration championshipRegistration =
                generateChampionshipRegistration(championshipRegistrationTO);
        if(championshipRegistration == null)
            return "Team or championship not found";
        return update(championshipRegistration, id);
    }

    public void delete(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw(new ChampionshipRegistrationNotFoundException(id));
    }
}

