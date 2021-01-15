package uniWork.f1app.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uniWork.f1app.Entities.Car;
import uniWork.f1app.Entities.Team;
import uniWork.f1app.Services.CarService;
import uniWork.f1app.Services.TeamService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TeamController {

    private final TeamService service;
    private final CarService carService;

    @GetMapping("/teams")
    public List<String> all() {
        return service.all();
    }

    @GetMapping("/teams/{id}")
    public String get(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/teamsRaw")
    public List<Team> raw() { return service.raw(); }

    @GetMapping("/teams/cars")
    public String cars() {
        return "Choose a team to see car list";
    }

    @GetMapping("/teams/cars/{name}")
    public String teamCar(@PathVariable String name) {
        return carService.findByTeam(name).toString() +
                "\nYou can see every car by adding year to path";
    }

    @GetMapping("/teams/cars/{name}/{year}")
    public String yearlyTeamCar(@PathVariable String name, @PathVariable Integer year) {
        return carService.findByTeamAndYear(name, year).toString();
    }

    @PostMapping("/teams")
    public Team save(@RequestBody Team team) {
        return service.save(team);
    }

    @PutMapping("/teams/{id}")
    public String update(@RequestBody Team team, @PathVariable String id) {
        return service.update(team, id);
    }

    @DeleteMapping("/teams/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
