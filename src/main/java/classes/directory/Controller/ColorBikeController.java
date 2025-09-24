package classes.directory.Controller;

import classes.directory.Entity.ColorBike;
import classes.directory.Repository.ColorBikeRepository;
import classes.directory.Service.ColorBikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class ColorBikeController {
    ColorBikeService colorBikeService;
    ColorBikeRepository colorBikeRepository;

    public ColorBikeController(ColorBikeService colorBikeService, ColorBikeRepository colorBikeRepository){
        this.colorBikeService = colorBikeService;
        this.colorBikeRepository = colorBikeRepository;
    }

    @GetMapping("/color/index")
    public String indexColor(Model model) {
        model.addAttribute("colors", colorBikeService.showColors());

        return "color/color_index";
    }

    @GetMapping("/color/new")
    public String newColor(Model model){
        model.addAttribute("colors", colorBikeService.showColors());

        return "color/new_color";
    }

    @PostMapping("/color/new")
    public String newColor(@ModelAttribute ColorBike colorBike){
        colorBikeService.createColor(colorBike);

        return "color/saved_color";
    }

    @GetMapping("/color/{id}/edit")
    public String editFormColor(@PathVariable Long id, Model model) {
        ColorBike colorBike = colorBikeService.throwExColor(id);
        model.addAttribute("colorBike",colorBike);
        return "color/edit_color";
    }

    @PostMapping("/color/{id}/edit")
    public String update(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String value
    ) throws Exception{
        try{
            colorBikeService.updateColor(id,name,value);
        }
        catch (Exception ex){
            System.out.println("That color don't exist");
        }

        return "redirect:/admin";
    }

    @GetMapping("/color/{id}")
    public String findAll(Model model, @PathVariable Long id){
        ColorBike colorBike = colorBikeService.throwExColor(id);
        model.addAttribute("colorBike", colorBike);

        return "show_color";
    }

    @GetMapping("/color/{id}/delete")
    public String deleteColor(@ModelAttribute ColorBike colorBike) {
        colorBikeService.deleteColor(colorBike);

        return "color/deleted_color";
    }
}
