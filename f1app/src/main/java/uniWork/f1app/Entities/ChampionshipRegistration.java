package uniWork.f1app.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class ChampionshipRegistration {

    private @Id String id;
    private Team team;
    private Championship championship;

    public ChampionshipRegistration(Team team, Championship championship) {
        this.team = team;
        this.championship = championship;
    }

    @Override
    public String toString() {
        return "Chamionship registration for team " + team.getName() + " at the " + championship.toString();
    }
}
