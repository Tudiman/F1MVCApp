package uniWork.f1app.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Team {

    private @Id String id;
    private String name;
    private Integer foundingYear;
    private String country;
    private String principal;

    public Team(String name, Integer foundingYear, String country, String principal) {
        this.name = name;
        this.foundingYear = foundingYear;
        this.country = country;
        this.principal = principal;
    }

    @Override
    public String toString() {
        return name + ", est. " + foundingYear + " in " + country + ", ran by " + principal;
    }
}
