package backend.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "farmdata")
public class FarmData {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;
    private String type;
    private double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FarmData(String date, String type, double value) {
        this.date = new Date(date);
        this.type = type;
        this.value = value;
    }

    public FarmData() {

    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
