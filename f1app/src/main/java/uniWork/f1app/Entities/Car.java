package uniWork.f1app.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Car implements Comparable<Car>{

    private @Id String id;
    private String team;
    private String name;
    private String engine;
    private Integer rating;
    private Integer year;

    public Car(String team, String name, String engine, Integer rating, Integer year) {
        this.team = team;
        this.name = name;
        this.engine = engine;
        this.rating = rating;
        this.year = year;
    }

    @Override
    public String toString() {
        return team + " " + name + ", " + engine + " engine" + ", " + rating + " performance rating, from year " + year;
    }

    @Override
    public int compareTo(Car c) {
        return c.rating.compareTo(rating);
    }
}
