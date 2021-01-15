package uniWork.f1app.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Driver {

    private @Id String id;
    private String firstName;
    private String lastName;
    private String country;
    private GregorianCalendar birthday;
    private Integer number;

    public Driver(String firstName, String lastName, String country, GregorianCalendar birthday, Integer number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthday = birthday;
        this.number = number;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        formatter.setLenient(false);
        return firstName + " " + lastName + ", " + number + ", born "
                + formatter.format(birthday.getTime()) + " in " + country;
    }
}
