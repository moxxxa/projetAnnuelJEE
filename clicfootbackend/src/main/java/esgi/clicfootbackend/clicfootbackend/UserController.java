package esgi.clicfootbackend.clicfootbackend;

import esgi.clicfootbackend.clicfootbackend.user.User;
import esgi.clicfootbackend.clicfootbackend.user.UserService;
import org.apache.coyote.Response;
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
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<User> deleteUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<User>(userService.deleteUser(user),HttpStatus.OK);
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user){
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }

}
