package classes.directory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
public class BikeController {
    private List<Bike> bikes = new ArrayList<>();

    public BikeController(){
        bikes.add(new Bike(1,"RX6", "40", "Mountain", "Cool Bike"));
        bikes.add(new Bike(2,"8RK", "60", "Normal", "Fast Bike"));
        bikes.add(new Bike(3,"49B", "120", "Electric", "Electric Bike"));
    }

    @GetMapping("/")
    public String showBike(Model model){
        model.addAttribute("bikes", bikes);

        return "index";
    }

    @PostMapping("/bike/new")
    public String newBike(Bike bike){
        int idBicicletaNueva = bikes.size() + 1;
        bike.setId(idBicicletaNueva);
        bikes.add(bike);

        return "saved_bike";
    }

    @GetMapping("/bike/{numBike}/edit")
    public String editFormBike(@PathVariable int numBike, Model model) {
        Bike bike = bikes.get(numBike - 1);
        model.addAttribute("numBike", numBike);
        model.addAttribute("model", bike.getModel());
        model.addAttribute("speed", bike.getSpeed());
        model.addAttribute("type", bike.getType());
        model.addAttribute("description", bike.getDescription());
        return "edit_bike";
    }

    @PostMapping("/bike/{numBike}/edit")
    public String changeBikeData(@PathVariable int numBike,
            @RequestParam String model,
            @RequestParam String speed,
            @RequestParam String type,
            @RequestParam String description
    )
    {
        Bike bike = bikes.get(numBike - 1);
        bike.setModel(model);
        bike.setSpeed(speed);
        bike.setType(type);
        bike.setDescription(description);
        return "redirect:/admin";
    }

    @GetMapping("/bike/{numBike}")
    public String showBike(Model model, @PathVariable int numBike){
        Bike bike = bikes.get(numBike - 1);

        model.addAttribute("bike", bike);
        model.addAttribute("numBike", numBike);

        return "show_bike";
    }

    @GetMapping("/bike/{numBike}/delete")
    public String deleteBike(Model model, @PathVariable int numBike) {

        bikes.remove(numBike - 1);

        return "deleted_bike";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("bikes", bikes);

        return "admin_page";
    }
}
