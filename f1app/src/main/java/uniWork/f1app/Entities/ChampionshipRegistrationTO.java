package uniWork.f1app.Entities;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class ChampionshipRegistrationTO {

    private String teamName;
    private Integer championshipYear;

    @Override
    public String toString() {
        return "ChampionshipRegistrationTO{" +
                "teamName='" + teamName + '\'' +
                ", championshipYear=" + championshipYear +
                '}';
    }
}
