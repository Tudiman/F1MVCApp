package uniWork.f1app.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uniWork.f1app.Entities.Team;
import uniWork.f1app.Exceptions.TeamNotFoundException;
import uniWork.f1app.Repositories.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository repository;

    public List<String> all() {
        return repository.findAll().stream().map(Team::toString).collect(Collectors.toList());
    }

    public String findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id))
                .toString();
    }

    public Team findByName(String name) {
        return repository.findByName(name);
    }

    public List<Team> raw() {
        return repository.findAll();
    }

    public Team save(Team team) {
        return repository.save(team);
    }

    public String update(Team updatedTeam, String id) {
        return repository.findById(id)
                .map(team -> {
                    team.setName(updatedTeam.getName());
                    team.setFoundingYear(updatedTeam.getFoundingYear());
                    team.setCountry(updatedTeam.getCountry());
                    team.setPrincipal(updatedTeam.getPrincipal());
                    return repository.save(team);
                }).orElseThrow(() -> new TeamNotFoundException(id))
                .toString();
    }

    public void delete(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw(new TeamNotFoundException(id));
    }
}
