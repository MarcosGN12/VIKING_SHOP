package classes.directory.Service;

import classes.directory.Entity.User;
import classes.directory.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> showUser(){
        return userRepository.findAll();
    }

    public void createUser(User user){userRepository.save(user);}

    public User findUserById(@PathVariable Long id) throws EntityNotFoundException{
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("that user don't exist"));
    }

    public void updateUser(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam int tlf
    )
    {
        User user = findUserById(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setTlf(tlf);

        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
