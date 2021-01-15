package uniWork.f1app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniWork.f1app.Entities.ChampionshipRegistration;

import java.util.List;

public interface ChampionshipRegistrationRepository extends MongoRepository<ChampionshipRegistration, String> {

    public List<ChampionshipRegistration> findByChampionshipYear(Integer year);
    public List<ChampionshipRegistration> findByTeamIdAndChampionshipYear(String id, Integer year);
}
