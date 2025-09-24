package classes.directory.Service;

import classes.directory.Entity.Bike;
import classes.directory.Entity.ColorBike;
import classes.directory.Repository.ColorBikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ColorBikeService {

    @Autowired
    private ColorBikeRepository colorBikeRepository;

    public List<ColorBike> showColors(){
        return colorBikeRepository.findAll();
    }

    public void createColor(ColorBike colorBike){
        colorBikeRepository.save(colorBike);
    }

    public ColorBike throwExColor(@PathVariable Long id){
        return colorBikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("that color don't exist"));
    }

    public void updateColor(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String value){

        ColorBike colorBike = throwExColor(id);
        colorBike.setName(name);
        colorBike.setValue(value);

        colorBikeRepository.save(colorBike);
    }

    public void deleteColor(ColorBike colorBike){
        colorBikeRepository.delete(colorBike);
    }
}
