package classes.directory.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;
    private String email;
    private Boolean role;
    private int tlf;
    private String name;
    private String surname;

    public User(int id, String password, String email, Boolean role, int tlf, String surname, String name) {
        super();
        this.id = id;
        this.password = password;
        this.email = email;
        this.role = role;
        this.tlf = tlf;
        this.surname = surname;
        this.name = name;
    }

    public User() {

    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Boolean getRole() {return role;}

    public void setRole(Boolean role) {this.role = role;}

    public int gettlf() {return tlf;}

    public void settlf(int tlf) {this.tlf = tlf;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", tlf=" + tlf +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
