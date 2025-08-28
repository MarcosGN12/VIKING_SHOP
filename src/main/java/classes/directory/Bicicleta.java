package classes.directory;

public class Bicicleta {
    private String user;
    private String name;
    private String speed;
    private String type;
    private String description;

    public Bicicleta() {

    }

    public Bicicleta(String user, String name, String speed, String type, String description){
        super();
        this.user = user;
        this.name = name;
        this.speed = speed;
        this.type = type;
        this.description = description;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSpeed(){
        return speed;
    }

    public void setSpeed(String speed){
        this.speed = speed;
    }

    public String getType(){return  type;}

    public void setType(String type){this.type = type;}

    public String getDescription(){return  description;}

    public void setDescription(String description){this.description = description;}

    @Override
    public String toString() {return "Post [user=" + user + ", name=" + name + ", speed=" + speed + ", tipo=" + type + " description=" + description + "]";}
}
