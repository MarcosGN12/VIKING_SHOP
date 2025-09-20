package classes.directory.Controller;

import classes.directory.Entity.ColorBike;
import classes.directory.Service.ColorBikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ColorBikeController {
    ColorBikeService colorBikeService;

    public ColorBikeController(ColorBikeService colorBikeService){
        this.colorBikeService = colorBikeService;
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
        colorBikeService.newColor(colorBike);

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
        colorBikeService.changeColorData(id,name,value);

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
        colorBikeService.deleteColor(colorBike);

        return "color/deleted_color";
    }

    private ColorBike findColorById(@RequestParam int id){
        for (ColorBike colorBike: colorBikeService.showColors()){
            if(colorBike.getId() == id){
                return colorBike;
            }
        }
        return null;
    }
}
