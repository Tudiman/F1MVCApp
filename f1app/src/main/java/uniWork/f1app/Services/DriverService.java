package uniWork.f1app.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uniWork.f1app.Entities.Driver;
import uniWork.f1app.Exceptions.DriverNotFoundException;
import uniWork.f1app.Repositories.DriverRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverService {

    private final DriverRepository repository;

    public List<String> all() {
        return repository.findAll().stream().map(Driver::toString).collect(Collectors.toList());
    }

    public String findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id))
                .toString();
    }

    public Driver findByName(String first, String last) {
        return repository.findByFirstNameAndLastName(first,last);
    }

    public List<Driver> raw() {
        return repository.findAll();
    }

    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    public String update(Driver updatedDriver, String id) {
        return repository.findById(id)
                .map(driver -> {
                    driver.setFirstName(updatedDriver.getFirstName());
                    driver.setLastName(updatedDriver.getLastName());
                    driver.setCountry(updatedDriver.getCountry());
                    driver.setBirthday(updatedDriver.getBirthday());
                    driver.setNumber(updatedDriver.getNumber());
                    return repository.save(driver);
                }).orElseThrow(() -> new DriverNotFoundException(id))
                .toString();
    }

    public void delete(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw(new DriverNotFoundException(id));
    }
}
