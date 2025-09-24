package classes.directory.Controller;

import classes.directory.Entity.TypeBike;
import classes.directory.Service.TypeBikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TypeBikeController {
    TypeBikeService typeBikeService;

    public TypeBikeController(TypeBikeService typeBikeService){
        this.typeBikeService = typeBikeService;
    }

    @GetMapping("/type/index")
    public String indexType(Model model) {
        model.addAttribute("types", typeBikeService.showTypes());

        return "type/type_index";
    }

    @GetMapping("/type/new")
    public String newType(Model model){
        model.addAttribute("types", typeBikeService.showTypes());

        return "type/new_type";
    }

    @PostMapping("/type/new")
    public String newType(@ModelAttribute TypeBike typeBike){
        typeBikeService.createType(typeBike);

        return "type/saved_type";
    }

    @GetMapping("/type/{id}/edit")
    public String editFormType(@PathVariable Long id, Model model) {
        TypeBike typeBike = typeBikeService.findTypeById(id);
        model.addAttribute("typeBike",typeBike);
        return "type/edit_type";
    }

    @PostMapping("/type/{id}/edit")
    public String updateType(
            @PathVariable Long id,
            @RequestParam String name
    ) throws Exception {
        try {
            typeBikeService.updateType(id,name);
        }
        catch (Exception ex){
            System.out.println("That type don't exist");
        }

        return "redirect:/admin";
    }

    @GetMapping("/type/{id}")
    public String showType(Model model, @PathVariable Long id){
        TypeBike typeBike = typeBikeService.findTypeById(id);
        model.addAttribute("typeBike", typeBike);

        return "show_type";
    }

    @GetMapping("/type/{id}/delete")
    public String deleteType(@ModelAttribute TypeBike typeBike) {
        typeBikeService.deleteType(typeBike);

        return "type/deleted_type";
    }
}
