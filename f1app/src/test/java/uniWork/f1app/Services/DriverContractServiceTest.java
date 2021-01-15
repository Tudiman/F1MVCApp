package uniWork.f1app.Services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uniWork.f1app.Entities.Driver;
import uniWork.f1app.Entities.DriverContract;
import uniWork.f1app.Entities.DriverContractTO;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@SpringBootTest
public class DriverContractServiceTest {

    @Autowired
    private DriverContractService driverContractService;

    @Test
    public void allHappyFlow() {
        List<?> strings = driverContractService.all();
        assertThat(strings != null);

        strings = driverContractService.raw();
        assertThat(strings != null);
    }

    @Test
    public void findByIdHappyFlow() {
        String id = driverContractService
                .findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate("Charles", "Leclerc",
                        "Scuderia Ferrari", 2015, 2016).getId();
        String string = driverContractService.findById(id);
        assertThat(string != null);
        DriverContract driverContract = driverContractService.findRawById(id);
        assertThat(driverContract != null);
    }

    @Test
    public void generateDriverContractHappyFlow() {
        DriverContractTO driverContractTO = new DriverContractTO("Lewis", "Hamilton",
                "Scuderia Ferrari", 2024, 2026);
        DriverContract driverContract = driverContractService.generateDriverContract(driverContractTO);
        assertThat(driverContract != null);
    }

    @Test
    public void generateDriverContractWrongData() {
        DriverContractTO driverContractTO = new DriverContractTO("Wrong", "Name",
                "Nonexistent team", 2024, 2026);
        DriverContract driverContract = driverContractService.generateDriverContract(driverContractTO);
        assertThat(driverContract == null);
    }

    @Test void saveHappyFlow() {
        DriverContractTO driverContractTO = new DriverContractTO("Lewis", "Hamilton",
                "Scuderia Ferrari", 2024, 2026);
        DriverContract driverContract = driverContractService.generateDriverContract(driverContractTO);
        String message = driverContractService.save(driverContractTO);
        String fn = driverContract.getDriver().getFirstName();
        String ln = driverContract.getDriver().getLastName();
        String t = driverContract.getTeam().getName();
        Integer s = driverContract.getStartDate();
        Integer e = driverContract.getEndDate();
        driverContractService.delete(driverContractService
                .findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate(fn, ln, t, s, e).getId());
        assertThat(message.equals(driverContract.toString() + " was saved"));

    }

    @Test void saveWrongData() {
        DriverContractTO driverContractTO = new DriverContractTO("Wrong", "Name",
                "Nonexistent team", 2024, 2026);
        DriverContract driverContract = driverContractService.generateDriverContract(driverContractTO);
        assertThat(driverContractService.save(driverContractTO).equals("Driver or team not found"));
    }

    @Test void saveExistingContract() {
        DriverContractTO driverContractTO = new DriverContractTO("Lewis", "Hamilton",
                "Scuderia Ferrari", 2015, 2018);
        DriverContract driverContract = driverContractService.generateDriverContract(driverContractTO);
        assertThat(driverContractService.save(driverContractTO).equals("Driver already has contract in this timeframe"));
    }

    @Test void saveFullTeam() {
        DriverContractTO driverContractTO = new DriverContractTO("Lewis", "Hamilton",
                "Scuderia Ferrari", 2021, 2022);
        DriverContract driverContract = driverContractService.generateDriverContract(driverContractTO);
        assertThat(driverContractService.save(driverContractTO).equals("Team already has maximum amount of drivers allowed"));
    }

    @Test void saveWrongInterval() {
        DriverContractTO driverContractTO = new DriverContractTO("Lewis", "Hamilton",
                "Scuderia Ferrari", 2021, 2019);
        DriverContract driverContract = driverContractService.generateDriverContract(driverContractTO);
        assertThat(driverContractService.save(driverContractTO).equals("Contract cannot end before starting"));
    }

    @Test void updateHappyFlow() {
        DriverContractTO driverContractTO = new DriverContractTO("Charles", "Leclerc",
                "Scuderia Ferrari", 2015, 2016);
        DriverContract driverContract = driverContractService.generateDriverContract(driverContractTO);
        String updatedDriverContractId = driverContractService
                .findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate("Charles", "Leclerc",
                        "Scuderia Ferrari", 2015, 2016).getId();
        assertThat(driverContractService.update(driverContractTO, updatedDriverContractId)
                .equals(driverContract.toString() + " was updated"));

    }

    @Test void updateWrongData() {
        DriverContractTO driverContractTO = new DriverContractTO("Wrong", "Name",
                "Nonexistent team", 2024, 2026);
        String updatedDriverContractId = driverContractService
                .findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate("Charles", "Leclerc",
                        "Scuderia Ferrari", 2015, 2016).getId();
        assertThat(driverContractService.update(driverContractTO, updatedDriverContractId)
                .equals("Driver or team not found"));
    }

    @Test void updateExistingContract() {
        DriverContractTO driverContractTO = new DriverContractTO("Lewis", "Hamilton",
                "Scuderia Ferrari", 2015, 2016);
        String updatedDriverContractId = driverContractService
                .findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate("Charles", "Leclerc",
                        "Scuderia Ferrari", 2015, 2016).getId();
        assertThat(driverContractService.update(driverContractTO, updatedDriverContractId)
                .equals("Driver already has contract in this timeframe"));
    }

    @Test void updateFullTeam() {
        DriverContractTO driverContractTO = new DriverContractTO("Lewis", "Hamilton",
                "Scuderia Ferrari", 2021, 2022);
        String updatedDriverContractId = driverContractService
                .findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate("Charles", "Leclerc",
                        "Scuderia Ferrari", 2015, 2016).getId();
        assertThat(driverContractService.update(driverContractTO, updatedDriverContractId)
                .equals("Team already has maximum amount of drivers allowed"));
    }

    @Test void updateWrongInterval() {
        DriverContractTO driverContractTO = new DriverContractTO("Lewis", "Hamilton",
                "Scuderia Ferrari", 2021, 2019);
        String updatedDriverContractId = driverContractService
                .findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate("Charles", "Leclerc",
                        "Scuderia Ferrari", 2015, 2016).getId();
        assertThat(driverContractService.update(driverContractTO, updatedDriverContractId)
                .equals("Contract cannot end before starting"));
    }

    @Test void updateSameContractWithCollidingData() {
        DriverContractTO driverContractTO = new DriverContractTO("Lewis", "Hamilton",
                "Mercedes-AMG Petronas F1 Team", 2015, 2020);
        DriverContract driverContract = driverContractService.generateDriverContract(driverContractTO);
        DriverContract updatedDriverContract = driverContractService
                .findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate("Lewis", "Hamilton",
                        "Mercedes-AMG Petronas F1 Team", 2015, 2020);
        String id = updatedDriverContract.getId();
        assertThat(driverContractService.update(driverContractTO, id)
                .equals(driverContract.toString() + " was updated"));
    }

    @Test void deleteHappyFlow() {
        DriverContract driverContract = driverContractService
                .findByDriverFirstNameAndDriverLastNameAndTeamNameAndStartDateAndEndDate("Charles", "Leclerc",
                        "Scuderia Ferrari", 2015, 2016);
        String id = driverContract.getId();
        String message = driverContractService.delete(id);
        assertThat(message.equals("Driver contract has been removed"));
        driverContractService.save(driverContract);
    }

}
