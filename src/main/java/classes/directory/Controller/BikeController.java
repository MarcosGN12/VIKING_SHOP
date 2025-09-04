package classes.directory.Controller;

import classes.directory.Entity.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
public class BikeController {
    private List<Bike> bikes = new ArrayList<>();

    public BikeController(){
        bikes.add(new Bike(1,"RX6", 40, "Mountain", "Cool Bike", "Blue"));
        bikes.add(new Bike(2,"8RKF", 60, "Normal", "Fast Bike", "Red"));
        bikes.add(new Bike(3,"49B", 120, "Electric", "Electric Bike", "Yellow"));
    }

    @GetMapping("/")
    public String showBike(Model model){
        model.addAttribute("bikes", bikes);

        return "index";
    }

    @PostMapping("/bike/new")
    public String newBike(Bike bike){
        int idNewBike = bikes.size() + 1;
        bike.setId(idNewBike);
        bikes.add(bike);

        return "saved_bike";
    }

    @GetMapping("/bike/{id}/edit")
    public String editFormBike(@PathVariable int id, Model model) {
        Bike bike = findBikeById(id);
        model.addAttribute("bike",bike);
        return "edit_bike";
    }

    @PostMapping("/bike/{id}/edit")
    public String changeBikeData(@PathVariable int id,
            @RequestParam String model,
            @RequestParam int price,
            @RequestParam String type,
            @RequestParam String description
    )
    {
        Bike bike = findBikeById(id);
        bike.setModel(model);
        bike.setPrice(price);
        bike.setType(type);
        bike.setDescription(description);

        return "redirect:/admin";
    }

    @GetMapping("/bike/{id}")
    public String showBike(Model model, @PathVariable int id){
        Bike bike = findBikeById(id);
        model.addAttribute("bike", bike);

        return "show_bike";
    }

    @GetMapping("/bike/{id}/delete")
    public String deleteBike(@PathVariable int id) {
        bikes.remove(findBikeById(id));

        return "deleted_bike";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("bikes", bikes);

        return "admin_page";
    }

    private Bike findBikeById(@RequestParam int id){
        for (Bike bike: bikes){
            if(bike.getId() == id){
                return bike;
            }
        }
        return null;
    }
}
