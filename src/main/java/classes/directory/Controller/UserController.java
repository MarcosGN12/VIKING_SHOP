package classes.directory.Controller;

import classes.directory.Entity.User;
import classes.directory.Repository.UserRepository;
import classes.directory.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class UserController {
    UserService userService;
    UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user/new")
    public String newUser(@ModelAttribute User user){
        userService.newUser(user);

        return "user/saved_user";
    }

    @GetMapping("/user/{id}/edit")
    public String editFormUser(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("that user don't exist"));
        model.addAttribute("user",user);
        return "user/edit_user";
    }

    @PostMapping("/user/{id}/edit")
    public String changeUserData(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam int tlf
    ) throws Exception
    {
        try{
            userService.update(id,name,surname,email,password,tlf);
        }
        catch (Exception ex){
            System.out.println("That user don't exist");
        }

        return "redirect:/admin";
    }

    @GetMapping("/user/{id}")
    public String showUser(Model model, @PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("that user don't exist"));
        model.addAttribute("user", user);

        return "user/show_user";
    }

    @GetMapping("/user/{id}/delete")
    public String deleteUser(@ModelAttribute User user) {
        userService.deleteUser(user);

        return "user/deleted_user";
    }
}
