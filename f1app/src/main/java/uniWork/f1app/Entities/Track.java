package uniWork.f1app.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Track {

    private @Id String id;
    private String name;
    private Integer length;
    private String country;
    private Integer inauguralYear;

    public Track(String name, Integer length, String country, Integer inauguralYear) {
        this.name = name;
        this.length = length;
        this.country = country;
        this.inauguralYear = inauguralYear;
    }

    @Override
    public String toString() {
        return name + ", " + length + "km, from " + country + ", inaugurated in year " + inauguralYear;
    }
}
