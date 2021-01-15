package uniWork.f1app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniWork.f1app.Entities.Car;
import uniWork.f1app.Entities.Team;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {
    public List<Car> findByTeam(String name);
    public List<Car> findByYear(Integer year);
    public Car findByTeamAndYear(String name, Integer year);
}
