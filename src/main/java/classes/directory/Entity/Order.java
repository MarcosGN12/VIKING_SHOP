package classes.directory.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private int price;

    @ManyToOne
    private Bike bikes;

    public Order(){

    }

    public Order(int id, Date date, int price, Bike bikes) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.bikes = bikes;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public Bike getBikes() {return bikes;}

    public void setBikes(Bike bikes) {this.bikes = bikes;}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                ", bikes=" + bikes +
                '}';
    }
}


