package uniWork.f1app.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class DriverContract {

    private @Id String id;
    private Driver driver;
    private Team team;
    private Integer startDate;
    private Integer endDate;

    public DriverContract(Driver driver, Team team, Integer startDate, Integer endDate) {
        this.driver = driver;
        this.team = team;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Driver contract for " + driver.getName() + " at team " + team.getName() +
                " from " + startDate + " to " + endDate;
    }
}
