package uniWork.f1app.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uniWork.f1app.Entities.*;
import uniWork.f1app.Services.ChampionshipRegistrationService;
import uniWork.f1app.Services.DriverContractService;
import uniWork.f1app.Services.DriverService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChampionshipRegistrationController {

    private final ChampionshipRegistrationService service;

    @GetMapping("/championshipRegistrations")
    public List<String> all() {
        return service.all();
    }

    @GetMapping("/championshipRegistrations/{id}")
    public String get(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/championshipRegistrationsRaw")
    public List<ChampionshipRegistration> raw() { return service.raw(); }

    @PostMapping("/championshipRegistrations")
    public String save(@RequestBody ChampionshipRegistrationTO championshipRegistrationTO) {
        return service.save(championshipRegistrationTO);
    }

    @PutMapping("/championshipRegistrations/{id}")
    public String update(@RequestBody ChampionshipRegistrationTO championshipRegistrationTO, @PathVariable String id) {
        return service.update(championshipRegistrationTO, id);
    }

    @DeleteMapping("/championshipRegistrations/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
