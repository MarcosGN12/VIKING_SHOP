package classes.directory.Controller;

import classes.directory.Entity.Bike;
import classes.directory.Service.BikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BikeController {
    BikeService bikeService;

    public BikeController(BikeService bikeService){
        this.bikeService = bikeService;
    }

    @GetMapping("/")
    public String showBike(Model model){
        model.addAttribute("bikes", bikeService.showBike());

        return "index";
    }

    @PostMapping("/bike/new")
    public String newBike(@ModelAttribute Bike bike){
        bikeService.newBike(bike);

        return "saved_bike";
    }

    @GetMapping("/bike/{id}/edit")
    public String editFormBike(@PathVariable int id, Model model) {
        Bike bike = findBikeById(id);
        model.addAttribute("bike",bike);
        return "edit_bike";
    }

    @PostMapping("/bike/{id}/edit")
    public String changeBikeData(
            @PathVariable Long id,
            @RequestParam String model,
            @RequestParam int price,
            @RequestParam String type,
            @RequestParam String description,
            @RequestParam String color
    )
    {
        bikeService.changeBikeData(id,model,price,type,description,color);

        return "redirect:/admin";
    }

    @GetMapping("/bike/{id}")
    public String showBike(Model model, @PathVariable int id){
        Bike bike = findBikeById(id);
        model.addAttribute("bike", bike);

        return "show_bike";
    }

    @GetMapping("/bike/{id}/delete")
    public String deleteBike(@ModelAttribute Bike bike) {
        bikeService.deleteBike(bike);

        return "deleted_bike";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("bikes", bikeService.showBike());

        return "admin_page";
    }

    private Bike findBikeById(@RequestParam int id){
        for (Bike bike: bikeService.showBike()){
            if(bike.getId() == id){
                return bike;
            }
        }
        return null;
    }
}
