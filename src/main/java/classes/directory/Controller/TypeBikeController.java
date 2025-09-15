package classes.directory.Controller;

import classes.directory.Entity.TypeBike;
import classes.directory.Service.TypeBikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TypeBikeController {
    TypeBikeService typesService;

    public TypeBikeController(TypeBikeService typesService){
        this.typesService = typesService;
    }

    @PostMapping("/type/new")
    public String newType(@ModelAttribute TypeBike typeBike){
        typesService.newType(typeBike);

        return "type/saved_type";
    }

    @GetMapping("/type/{id}/edit")
    public String editFormType(@PathVariable int id, Model model) {
        TypeBike typeBike = findTypeById(id);
        model.addAttribute("typeBike",typeBike);
        return "type/edit_type";
    }

    @PostMapping("/type/{id}/edit")
    public String changeTypeData(
            @PathVariable Long id,
            @RequestParam String name
    )
    {
        typesService.changeTypeData(id,name);

        return "redirect:/admin";
    }

    @GetMapping("/type/{id}")
    public String showType(Model model, @PathVariable int id){
        TypeBike typeBike = findTypeById(id);
        model.addAttribute("typeBike", typeBike);

        return "show_type";
    }

    @GetMapping("/type/{id}/delete")
    public String deleteType(@ModelAttribute TypeBike typeBike) {
        typesService.deleteType(typeBike);

        return "type/deleted_type";
    }

    private TypeBike findTypeById(@RequestParam int id){
        for (TypeBike typeBike: typesService.showTypes()){
            if(typeBike.getId() == id){
                return typeBike;
            }
        }
        return null;
    }
}
