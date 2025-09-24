package classes.directory.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ColorBike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String value;

    public ColorBike(){

    }

    public ColorBike(Long id, String name, String value){
        super();
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getValue() {return value;}

    public void setValue(String value) {this.value = value;}

    @Override
    public String toString() {
        return "Colors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
