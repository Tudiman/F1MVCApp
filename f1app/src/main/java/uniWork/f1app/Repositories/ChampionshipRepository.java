package uniWork.f1app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniWork.f1app.Entities.Championship;

public interface ChampionshipRepository extends MongoRepository<Championship, String> {
    public Championship findByYear(Integer year);
}
