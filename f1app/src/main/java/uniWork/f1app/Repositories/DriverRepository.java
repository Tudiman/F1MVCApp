package uniWork.f1app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniWork.f1app.Entities.Driver;

public interface DriverRepository extends MongoRepository<Driver, String> {
    public Driver findByFirstNameAndLastName(String n1, String n2);
}
