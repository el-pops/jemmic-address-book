package popov.ilia.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import java.util.Optional;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Contact implements Comparable<Contact> {

    private String category;

    private String surname;

    private String name;

    private String telephoneNumber;

    private String email;


    private Integer age;

    private String hairColor;

    @JsonIgnore
    public String getFullName() {
        return getSurname() + getName();
    }

    @JsonIgnore
    public String getBasicFormatedPersonInfo() {
        return String.format("%-16s %-16s", surname, name);
    }

    @JsonIgnore
    public String getFullFormatedPersonInfo() {
        return String.format("%-16s %-16s %-16s %-16s %-16s %-16s %-16s", category, surname, name, telephoneNumber, email, getAge().isPresent() ? getAge().get() : "*", getHairColor().orElse("*"));
    }

    @Override
    public int compareTo(Contact p) {
        return String.CASE_INSENSITIVE_ORDER.compare(getSurname(), p.getSurname());
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getHairColor() {
        return Optional.ofNullable(hairColor);
    }
}
