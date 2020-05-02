package esgi.clicfootbackend.clicfootbackend;

import esgi.clicfootbackend.clicfootbackend.user.User;
import esgi.clicfootbackend.clicfootbackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User createUser(@Valid @RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
