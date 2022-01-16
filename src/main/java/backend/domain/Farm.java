package backend.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "farm")
public class Farm {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @OneToMany
    private List<FarmData> data;

    // getter, setters, constructors
    public Farm() {

    }

    public Farm(List<FarmData> data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FarmData> getData() {
        return data;
    }

    public void setData(List<FarmData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Farm{" + "id=" + id + ", name='" + name + "'}";
    }
}
