package uniWork.f1app.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uniWork.f1app.Entities.Car;
import uniWork.f1app.Exceptions.CarNotFoundException;
import uniWork.f1app.Repositories.CarRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository repository;

    public List<String> all() {
        return repository.findAll().stream().map(Car::toString).collect(Collectors.toList());
    }

    public String findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id))
                .toString();
    }

    public List<Car> findByTeam(String name) {
        return repository.findByTeam(name);
    }

    public Car findByTeamAndYear(String name, Integer year) {
        return repository.findByTeamAndYear(name, year);
    }

    public List<Car> sortByYear(Integer year) {
        List<Car> cars = repository.findByYear(year);
        Collections.sort(cars);
        return cars;
    }

    public List<Car> raw() {
        return repository.findAll();
    }

    public Car save(Car car) {
        return repository.save(car);
    }

    public String update(Car updatedCar, String id) {
        return repository.findById(id)
                .map(car -> {
                    car.setTeam(updatedCar.getTeam());
                    car.setName(updatedCar.getName());
                    car.setEngine(updatedCar.getEngine());
                    car.setRating(updatedCar.getRating());
                    car.setYear(updatedCar.getYear());
                    return repository.save(car);
                }).orElseThrow(() -> new CarNotFoundException(id))
                .toString();
    }

    public void delete(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw(new CarNotFoundException(id));
    }
}

