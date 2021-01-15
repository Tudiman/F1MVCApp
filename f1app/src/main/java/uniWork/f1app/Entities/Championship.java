package uniWork.f1app.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Championship {

    private @Id String id;
    private Integer year;

    public Championship(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return year + " Formula One World Championship";
    }
}
