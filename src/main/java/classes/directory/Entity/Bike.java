package classes.directory.Entity;

import javax.persistence.*;


@Entity
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private int price;
    @ManyToOne
    private TypeBike typeBike;
    private String description;
    @ManyToOne
    private ColorBike colorBike;

    public Bike() {

    }

    public Bike(Long id, String model, int price, TypeBike typeBike, String description, ColorBike colorBike) {
        super();
        this.id = id;
        this.model = model;
        this.price = price;
        this.typeBike = typeBike;
        this.description = description;
        this.colorBike = colorBike;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {return price;}

    public void setPrice(int price) {
        this.price = price;
    }

    public TypeBike getTypeBike() {
        return typeBike;
    }

    public void setTypeBike(TypeBike typeBike) {
        this.typeBike = typeBike;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ColorBike getColorBike() {return colorBike;}

    public void setColorBike(ColorBike colorBike) {this.colorBike = colorBike;}

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                ", typeBike='" + typeBike + '\'' +
                ", description='" + description + '\'' +
                ", colorBike='" + colorBike + '\'' +
                '}';
    }
}
