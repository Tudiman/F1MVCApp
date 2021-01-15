package uniWork.f1app.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uniWork.f1app.Entities.Car;
import uniWork.f1app.Services.CarService;

import java.util.List;

@RestController
@AllArgsConstructor
public class CarController {

    private final CarService service;

    @GetMapping("/cars")
    public List<String> all() {
        return service.all();
    }

    @GetMapping("/cars/{id}")
    public String get(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/carsRaw")
    public List<Car> raw() { return service.raw(); }

    @GetMapping("/cars/sort")
    public String sortCars() {
        return "Choose a year to sort by";
    }

    @GetMapping("/cars/sort/{year}")
    public String sortCarsByYear(@PathVariable Integer year) {
        return service.sortByYear(year).toString();
    }

    @PostMapping("/cars")
    public Car save(@RequestBody Car car) {
        return service.save(car);
    }

    @PutMapping("/cars/{id}")
    public String update(@RequestBody Car car, @PathVariable String id) {
        return service.update(car, id);
    }

    @DeleteMapping("/cars/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
