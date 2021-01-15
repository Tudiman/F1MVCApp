package uniWork.f1app.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uniWork.f1app.Entities.DriverContract;
import uniWork.f1app.Entities.DriverContractTO;
import uniWork.f1app.Services.DriverContractService;

import java.util.List;

@RestController
@AllArgsConstructor
public class DriverContractController {

    private final DriverContractService service;

    @GetMapping("/driverContracts")
    public List<String> all() {
        return service.all();
    }

    @GetMapping("/driverContracts/{id}")
    public String get(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/driverContractsRaw")
    public List<DriverContract> raw() { return service.raw(); }

    @PostMapping("/driverContracts")
    public String save(@RequestBody DriverContractTO driverContractTO) {
        return service.save(driverContractTO);
    }

    @PutMapping("/driverContracts/{id}")
    public String update(@RequestBody DriverContractTO driverContractTO, @PathVariable String id) {
        return service.update(driverContractTO, id);
    }

    @DeleteMapping("/driverContracts/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
