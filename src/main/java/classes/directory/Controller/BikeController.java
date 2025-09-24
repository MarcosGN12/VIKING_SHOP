package classes.directory.Controller;

import classes.directory.Entity.Bike;
import classes.directory.Entity.ColorBike;
import classes.directory.Entity.TypeBike;
import classes.directory.Repository.BikeRepository;
import classes.directory.Service.BikeService;
import classes.directory.Service.ColorBikeService;
import classes.directory.Service.TypeBikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class BikeController {
    BikeService bikeService;
    BikeRepository bikeRepository;
    ColorBikeService colorBikeService;
    TypeBikeService typeBikeService;

    public BikeController(BikeService bikeService, BikeRepository bikeRepository, ColorBikeService colorBikeService, TypeBikeService typeBikeService){
        this.bikeService = bikeService;
        this.bikeRepository = bikeRepository;
        this.colorBikeService = colorBikeService;
        this.typeBikeService = typeBikeService;
    }

    @GetMapping("/")
    public String findAll(Model model){
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
    public String editFormBike(@PathVariable Long id, Model model) {
        Bike bike = bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("that bike don't exist"));
        model.addAttribute("bike",bike);
        model.addAttribute("types",typeBikeService.showTypes());
        model.addAttribute("colors",colorBikeService.showColors());

        return "bike/edit_bike";
    }

    @PostMapping("/bike/{id}/edit")
    public String update(
            @PathVariable Long id,
            @RequestParam String model,
            @RequestParam int price,
            @RequestParam TypeBike typeBike,
            @RequestParam String description,
            @RequestParam ColorBike colorBike
    ) throws Exception {
        try {
            bikeService.update(id,model,price,typeBike,description,colorBike);
        }
        catch (Exception ex){
            System.out.println("That bike don't exist");
        }

        return "redirect:/admin";
    }

    @GetMapping("/bike/{id}")
    public String showBike(Model model, @PathVariable Long id){
        Bike bike = bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("That id doesn't exist"));
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

}
