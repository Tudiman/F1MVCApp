package uniWork.f1app.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uniWork.f1app.Entities.Championship;
import uniWork.f1app.Entities.Driver;
import uniWork.f1app.Services.ChampionshipService;
import uniWork.f1app.Services.DriverService;

import java.util.List;

@RestController
@AllArgsConstructor
public class DriverController {

    private final DriverService service;
    private final ChampionshipService championshipService;

    @GetMapping("/drivers")
    public List<String> all() {
        return service.all();
    }

    @GetMapping("/drivers/{id}")
    public String get(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/driversRaw")
    public List<Driver> raw() { return service.raw(); }

    @GetMapping("/drivers/mostTitles")
    public String mostTitles() {
        return championshipService.mostTitles();
    }

    @PostMapping("/drivers")
    public Driver save(@RequestBody Driver driver) {
        return service.save(driver);
    }

    @PutMapping("/drivers/{id}")
    public String update(@RequestBody Driver driver, @PathVariable String id) {
        return service.update(driver, id);
    }

    @DeleteMapping("/drivers/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
