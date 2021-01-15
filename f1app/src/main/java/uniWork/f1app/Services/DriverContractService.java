package uniWork.f1app.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uniWork.f1app.Entities.Driver;
import uniWork.f1app.Entities.DriverContract;
import uniWork.f1app.Entities.DriverContractTO;
import uniWork.f1app.Entities.Team;
import uniWork.f1app.Exceptions.DriverContractNotFoundException;
import uniWork.f1app.Repositories.DriverContractRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverContractService {

    private final DriverContractRepository repository;
    private final DriverService driverService;
    private final TeamService teamService;

    public List<String> all() {
        return repository.findAll().stream().map(DriverContract::toString).collect(Collectors.toList());
    }

    public String findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DriverContractNotFoundException(id))
                .toString();
    }

    public List<DriverContract> raw() {
        return repository.findAll();
    }

    public DriverContract findRawById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DriverContractNotFoundException(id));
    }

    public DriverContract findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate(
            String first, String last, String team, Integer start, Integer end) {
        return repository.findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate(first, last,
                team, start, end);
    }

    public DriverContract generateDriverContract(DriverContractTO driverContractTO) {
        Driver driver = driverService.findByName(
                driverContractTO.getDriverFirstName(), driverContractTO.getDriverLastName());
        if(driver == null)
            return null;
        Team team = teamService.findByName(driverContractTO.getTeamName());
        if(team == null)
            return null;
        return new DriverContract(driver, team,
                driverContractTO.getStartDate(), driverContractTO.getEndDate());
    }

    private boolean checkOverlap(DriverContract a, DriverContract b) {
        return a.getStartDate() <= b.getEndDate() && a.getEndDate() >= b.getStartDate();
    }

    private boolean checkTeamAvailability(List<DriverContract> list) {

        for(int i = 0; i < list.size() - 1; i++) {
            for(int j = i + 1; j < list.size(); j++)
                if(checkOverlap(list.get(i), list.get(j)))
                    return false;
        }
        return true;
    }

    public String save(DriverContract driverContract) {

        if(driverContract.getStartDate() > driverContract.getEndDate())
            return "Contract cannot end before starting";

        List<DriverContract> teamContracts = repository.findByTeamIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                driverContract.getTeam().getId(), driverContract.getEndDate(), driverContract.getStartDate()
        );
        if(!checkTeamAvailability(teamContracts))
            return "Team already has maximum amount of drivers allowed";

        if(repository.findByDriverIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                driverContract.getDriver().getId(), driverContract.getEndDate(), driverContract.getStartDate())
                .size() > 0)
            return "Driver already has contract in this timeframe";

        return repository.save(driverContract) + " was saved";
    }

    public String save(DriverContractTO driverContractTO) {
        DriverContract driverContract = generateDriverContract(driverContractTO);
        if(driverContract == null)
            return "Driver or team not found";
        return save(driverContract);
    }

    public String update(DriverContract updatedDriverContract, String id) {

        if(updatedDriverContract.getStartDate() > updatedDriverContract.getEndDate())
            return "Contract cannot end before starting";

        DriverContract contractToBeUpdated = repository.findById(id)
                .orElseThrow(() -> new DriverContractNotFoundException(id));

        List<DriverContract> teamContracts =
                repository.findByTeamIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIdIsNot(
                        updatedDriverContract.getTeam().getId(),
                        updatedDriverContract.getEndDate(), updatedDriverContract.getStartDate(),
                        contractToBeUpdated.getId()
        );
        if(!checkTeamAvailability(teamContracts))
            return "Team already has maximum amount of drivers allowed";

        if(repository.findByDriverIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIdIsNot(
                updatedDriverContract.getDriver().getId(),
                updatedDriverContract.getEndDate(), updatedDriverContract.getStartDate(), contractToBeUpdated.getId())
                .size() > 0)
            return "Driver already has contract in this timeframe";

        return repository.findById(id)
                .map(driverContract -> {
                    driverContract.setDriver(updatedDriverContract.getDriver());
                    driverContract.setTeam(updatedDriverContract.getTeam());
                    driverContract.setStartDate(updatedDriverContract.getStartDate());
                    driverContract.setEndDate(updatedDriverContract.getEndDate());
                    return repository.save(driverContract) + " was updated";
                }).orElseThrow(() -> new DriverContractNotFoundException(id));
    }

    public String update(DriverContractTO driverContractTO, String id) {
        DriverContract driverContract = generateDriverContract(driverContractTO);
        if(driverContract == null)
            return "Driver or team not found";
        return update(driverContract, id);
    }

    public String delete(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "Driver contract has been removed";
        }
        else throw(new DriverContractNotFoundException(id));
    }
}

