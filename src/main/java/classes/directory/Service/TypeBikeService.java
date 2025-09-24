package classes.directory.Service;

import classes.directory.Entity.TypeBike;
import classes.directory.Repository.TypeBikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TypeBikeService {

    @Autowired
    private TypeBikeRepository typeBikeRepository;

    public List<TypeBike> showTypes(){
        return typeBikeRepository.findAll();
    }

    public void createType(TypeBike typeBike){
        typeBikeRepository.save(typeBike);
    }

    public TypeBike findTypeById(@PathVariable Long id) throws EntityNotFoundException{
        return typeBikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("that type don't exist"));
    }

    public void updateType(
            @PathVariable Long id,
            @RequestParam String name){

        TypeBike typeBike = findTypeById(id);
        typeBike.setName(name);

        typeBikeRepository.save(typeBike);
    }

    public void deleteType(TypeBike typeBike){
        typeBikeRepository.delete(typeBike);
    }
}
