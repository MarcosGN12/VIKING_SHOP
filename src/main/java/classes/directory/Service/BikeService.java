package classes.directory.Service;

import classes.directory.Entity.Bike;
import classes.directory.Entity.ColorBike;
import classes.directory.Entity.TypeBike;
import classes.directory.Repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> showBike(){
        return bikeRepository.findAll();
    }

    public void createBike(Bike bike){
        bikeRepository.save(bike);
    }

    public Bike throwExBike(@PathVariable Long id){
        return bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("that bike don't exist"));
    }

    public void updateBike(
            @PathVariable Long id,
            @RequestParam String model,
            @RequestParam int price,
            @RequestParam TypeBike typeBike,
            @RequestParam String description,
            @RequestParam ColorBike colorBike
    ){

        Bike bike = throwExBike(id);
        bike.setModel(model);
        bike.setPrice(price);
        bike.setTypeBike(typeBike);
        bike.setDescription(description);
        bike.setColorBike(colorBike);

        bikeRepository.save(bike);
    }

    public void deleteBike(Bike bike){
        bikeRepository.delete(bike);
    }
}
