package classes.directory.Controller;

import classes.directory.Entity.ColorBike;
import classes.directory.Service.ColorBikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ColorBikeController {
    ColorBikeService colorsService;

    public ColorBikeController(ColorBikeService colorsService){
        this.colorsService = colorsService;
    }

    @PostMapping("/color/new")
    public String newColor(@ModelAttribute ColorBike colorBike){
        colorsService.newColor(colorBike);

        return "color/saved_color";
    }

    @GetMapping("/color/{id}/edit")
    public String editFormColor(@PathVariable int id, Model model) {
        ColorBike colorBike = findColorById(id);
        model.addAttribute("colorBike",colorBike);
        return "color/edit_color";
    }

    @PostMapping("/color/{id}/edit")
    public String changeColorData(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String value
    )
    {
        colorsService.changeColorData(id,name,value);

        return "redirect:/admin";
    }

    @GetMapping("/color/{id}")
    public String showColor(Model model, @PathVariable int id){
        ColorBike colorBike = findColorById(id);
        model.addAttribute("colorBike", colorBike);

        return "show_color";
    }

    @GetMapping("/color/{id}/delete")
    public String deleteColor(@ModelAttribute ColorBike colorBike) {
        colorsService.deleteColor(colorBike);

        return "color/deleted_color";
    }

    private ColorBike findColorById(@RequestParam int id){
        for (ColorBike colorBike: colorsService.showColors()){
            if(colorBike.getId() == id){
                return colorBike;
            }
        }
        return null;
    }
}
