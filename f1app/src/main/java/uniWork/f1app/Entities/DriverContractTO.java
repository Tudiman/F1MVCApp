package uniWork.f1app.Entities;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class DriverContractTO {

    private String driverFirstName;
    private String driverLastName;
    private String teamName;
    private Integer startDate;
    private Integer endDate;

    @Override
    public String toString() {
        return "DriverContractTO{" +
                "driverFirstName='" + driverFirstName + '\'' +
                ", driverLastName='" + driverLastName + '\'' +
                ", teamName='" + teamName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
