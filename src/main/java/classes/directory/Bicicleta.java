package classes.directory;

public class Bicicleta {
    private String model;
    private String speed;
    private String type;
    private String description;

    public Bicicleta() {

    }

    public Bicicleta(String model, String speed, String type, String description){
        super();
        this.model = model;
        this.speed = speed;
        this.type = type;
        this.description = description;
    }

    public String getName(){
        return model;
    }

    public void setName(String model){
        this.model = model;
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
    public String toString() {return "Post [name=" + model + ", speed=" + speed + ", tipo=" + type + " description=" + description + "]";}
}
