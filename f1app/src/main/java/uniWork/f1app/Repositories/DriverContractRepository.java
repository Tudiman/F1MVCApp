package uniWork.f1app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniWork.f1app.Entities.DriverContract;

import java.util.List;

public interface DriverContractRepository extends MongoRepository<DriverContract, String> {
    public List<DriverContract> findByTeamIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String id, Integer endDate, Integer startDate);
    public List<DriverContract>
    findByTeamIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIdIsNot(
            String id, Integer endDate, Integer startDate, String id2);
    public List<DriverContract> findByDriverIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String id, Integer endDate, Integer startDate);
    public List<DriverContract>
    findByDriverIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIdIsNot(
            String id, Integer endDate, Integer startDate, String id2);
    public DriverContract findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate(
            String fn, String ln, String t, Integer s, Integer e);
}
