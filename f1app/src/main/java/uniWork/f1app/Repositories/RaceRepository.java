package uniWork.f1app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniWork.f1app.Entities.Race;

import java.util.List;

public interface RaceRepository extends MongoRepository<Race, String> {

    public List<Race> findByYear(Integer year);
}
