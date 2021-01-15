package uniWork.f1app.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Race {

    private @Id String id;
    private String name;
    private Integer year;
    private String track;
    private List<String> rankings;

    public Race(String name, Integer year, String track, List<String> rankings) {
        this.name = name;
        this.year = year;
        this.track = track;
        this.rankings = rankings;
    }

    @Override
    public String toString() {
        return year + " " + name;
    }
}
