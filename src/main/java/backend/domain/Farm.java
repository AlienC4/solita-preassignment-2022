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

    // getter, setters, contructors
    public Farm(List<FarmData> data) {
        this.data = data;
    }

    public Farm() {

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


    @Override
    public String toString() {
        return "Farm{" + "id=" + id + ", name='" + name + '\'' + ", email='}";
    }
}
