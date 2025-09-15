package classes.directory.Controller;

import classes.directory.Entity.User;
import classes.directory.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/new")
    public String newUser(@ModelAttribute User user){
        userService.newUser(user);

        return "user/saved_user";
    }

    @GetMapping("/user/{id}/edit")
    public String editFormUser(@PathVariable int id, Model model) {
        User user = findUserById(id);
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
    )
    {
        userService.changeUserData(id,name,surname,email,password,tlf);

        return "redirect:/admin";
    }

    @GetMapping("/user/{id}")
    public String showUser(Model model, @PathVariable int id){
        User user = findUserById(id);
        model.addAttribute("user", user);

        return "user/show_user";
    }

    @GetMapping("/user/{id}/delete")
    public String deleteUser(@ModelAttribute User user) {
        userService.deleteUser(user);

        return "user/deleted_user";
    }

    private User findUserById(@RequestParam int id){
        for (User user: userService.showUser()){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
}
