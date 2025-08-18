package classes.directory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private List<Bicicleta> bicicletas = new ArrayList<>();

    public PostController(){
        bicicletas.add(new Bicicleta("RX6", "40", "Mountain", "Cool Bike"));
        bicicletas.add(new Bicicleta("8RK", "60", "Normal", "Fast Bike"));
        bicicletas.add(new Bicicleta("49B", "120", "Electric", "Electric Bike"));
    }

    @GetMapping("/")
    public String showBicicletas(Model model){
        model.addAttribute("bicicletas", bicicletas);

        return "index";
    }

    @PostMapping("/bicicleta/new")
    public String newPost(Model model, Bicicleta bicicleta){
        bicicletas.add(bicicleta);

        return "saved_post";
    }

    @GetMapping("/bicicleta/{numPost}")
    public String showPost(Model model, @PathVariable int numPost){
        Bicicleta bicicleta = bicicletas.get(numPost - 1);

        model.addAttribute("bicicleta", bicicleta);
        model.addAttribute("numPost", numPost);

        return "show_post";
    }

    @GetMapping("/bicicleta/{numPost}/delete")
    public String deletePost(Model model, @PathVariable int numPost) {

        bicicletas.remove(numPost - 1);

        return "deleted_post";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("bicicletas", bicicletas);

        return "admin_page";
    }
}
