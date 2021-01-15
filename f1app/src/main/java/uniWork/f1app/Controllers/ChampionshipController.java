package uniWork.f1app.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uniWork.f1app.Entities.Championship;
import uniWork.f1app.Services.ChampionshipService;
import uniWork.f1app.Services.RaceService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChampionshipController {

    private final ChampionshipService service;
    private final RaceService raceService;

    @GetMapping("/championships")
    public List<String> all() {
        return service.all();
    }

    @GetMapping("/championships/{id}")
    public String get(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/championshipsRaw")
    public List<Championship> raw() { return service.raw(); }

    @GetMapping("/championships/rankings")
    public String rankings() {
        return "You can view rankings by year";
    }

    @GetMapping("/championships/rankings/{year}")
    public String yearRanking(@PathVariable Integer year) {
        return raceService.getSeasonRankings(year).toString();
    }

    @GetMapping("/championships/champions")
    public String champions() {
        return service.champions().toString();
    }

    @GetMapping("/championships/champions/{year}")
    public String champion(@PathVariable Integer year) {
        return service.champion(year);
    }

    @PostMapping("/championships")
    public Championship save(@RequestBody Championship championship) {
        return service.save(championship);
    }

    @PutMapping("/championships/{id}")
    public String update(@RequestBody Championship championship, @PathVariable String id) {
        return service.update(championship, id);
    }

    @DeleteMapping("/championships/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
