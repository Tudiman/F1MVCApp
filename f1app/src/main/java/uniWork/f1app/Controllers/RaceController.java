package uniWork.f1app.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uniWork.f1app.Entities.Race;
import uniWork.f1app.Services.RaceService;

import java.util.List;

@RestController
@AllArgsConstructor
public class RaceController {

    private final RaceService service;

    @GetMapping("/races")
    public List<String> all() {
        return service.all();
    }

    @GetMapping("/races/{id}")
    public String get(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/racesRaw")
    public List<Race> raw() { return service.raw(); }

    @PostMapping("/races")
    public Race save(@RequestBody Race race) {
        return service.save(race);
    }

    @PutMapping("/races/{id}")
    public String update(@RequestBody Race race, @PathVariable String id) {
        return service.update(race, id);
    }

    @DeleteMapping("/races/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
