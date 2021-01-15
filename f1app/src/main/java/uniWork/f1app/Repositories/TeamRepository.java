package uniWork.f1app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniWork.f1app.Entities.Team;

public interface TeamRepository extends MongoRepository<Team, String> {
    public Team findByName(String n);
}
