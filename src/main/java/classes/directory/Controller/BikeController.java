package classes.directory.Controller;

import classes.directory.Entity.Bike;
import classes.directory.Entity.ColorBike;
import classes.directory.Entity.TypeBike;
import classes.directory.Service.BikeService;
import classes.directory.Service.ColorBikeService;
import classes.directory.Service.TypeBikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BikeController {
    BikeService bikeService;
    ColorBikeService colorBikeService;
    TypeBikeService typeBikeService;

    public BikeController(BikeService bikeService, ColorBikeService colorBikeService, TypeBikeService typeBikeService){
        this.bikeService = bikeService;
        this.colorBikeService = colorBikeService;
        this.typeBikeService = typeBikeService;
    }

    @GetMapping("/")
    public String showBike(Model model){
        model.addAttribute("bikes", bikeService.showBike());
        model.addAttribute("types", typeBikeService.showTypes());
        model.addAttribute("colors", colorBikeService.showColors());

        return "index";
    }

    @GetMapping("/bike/index")
    public String indexBike(Model model) {
        model.addAttribute("bikes", bikeService.showBike());

        return "bike/bike_index";
    }

    @GetMapping("/bike/new")
    public String newBike(Model model){
        model.addAttribute("types", typeBikeService.showTypes());
        model.addAttribute("colors", colorBikeService.showColors());

        return "bike/new_bike";
    }

    @PostMapping("/bike/new")
    public String savedBike(@ModelAttribute Bike bike){
        bikeService.newBike(bike);

        return "bike/saved_bike";
    }

    @GetMapping("/bike/{id}/edit")
    public String editFormBike(@PathVariable int id, Model model) {
        Bike bike = findBikeById(id);
        model.addAttribute("bike",bike);
        model.addAttribute("types",typeBikeService.showTypes());
        model.addAttribute("colors",colorBikeService.showColors());

        return "bike/edit_bike";
    }

    @PostMapping("/bike/{id}/edit")
    public String changeBikeData(
            @PathVariable Long id,
            @RequestParam String model,
            @RequestParam int price,
            @RequestParam TypeBike typeBike,
            @RequestParam String description,
            @RequestParam ColorBike colorBike
    )
    {
        bikeService.changeBikeData(id,model,price,typeBike,description,colorBike);

        return "redirect:/admin";
    }

    @GetMapping("/bike/{id}")
    public String showBike(Model model, @PathVariable int id){
        Bike bike = findBikeById(id);
        model.addAttribute("bike", bike);

        return "bike/show_bike";
    }

    @GetMapping("/bike/{id}/delete")
    public String deleteBike(@ModelAttribute Bike bike) {
        bikeService.deleteBike(bike);

        return "bike/deleted_bike";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("bikes", bikeService.showBike());
        model.addAttribute("types", typeBikeService.showTypes());
        model.addAttribute("colors", colorBikeService.showColors());

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
