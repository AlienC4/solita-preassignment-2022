package backend.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "farmdata")
public class FarmData {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime date;
    @Column
    private String type;
    @Column
    private double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FarmData(String date, String type, double value) {
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        this.type = type;
        this.value = value;
    }

    public FarmData() {

    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public static boolean validate(String type, double value) {
        switch (type) {
            case "pH":
                return 0 <= value && value <= 14;
            case "temperature":
                return -50 <= value && value <= 100;
            case "rainFall":
                return 0 <= value && value <= 500;
            default:
                return false;
        }
    }
}
