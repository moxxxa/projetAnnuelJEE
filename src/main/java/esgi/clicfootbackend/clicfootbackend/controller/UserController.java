package esgi.clicfootbackend.clicfootbackend.controller;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import esgi.clicfootbackend.clicfootbackend.Model.AuthentificationModel;
import esgi.clicfootbackend.clicfootbackend.Model.DeleteUserModel;
import esgi.clicfootbackend.clicfootbackend.configuration.UserConfig;
import esgi.clicfootbackend.clicfootbackend.error.UserAlreadyExistException;
import esgi.clicfootbackend.clicfootbackend.Model.User;
import esgi.clicfootbackend.clicfootbackend.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.apache.logging.log4j.Logger;


import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@RestController
public class UserController {

    private static Logger logger;
    static {
        try {
            // you need to do something like below instaed of Logger.getLogger(....);
            logger = LogManager.getLogger(UserController.class);
        } catch (Throwable th) {
        }
    }
    @Autowired
    private UserService userService;

    @Autowired
    private UserConfig userConfig;

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<Object> HandleUserAlreadyExist(Exception e){
        return new ResponseEntity<Object>(
                e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws NoSuchAlgorithmException {
        logger.info("processing to createUser ...");
        User createdUser = userService.createUser(user);
        createdUser.setPassword("");
        return new ResponseEntity<User>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/googleregistre")
    public ResponseEntity<User> createUser(@Valid @RequestBody String idTokenString) throws NoSuchAlgorithmException{
        logger.info("received token from the front side " + idTokenString);
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("236557997475-brjtalksspr9epeksd8amep2hl5ddeqn.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();
            // Print user identifier
            String userId = payload.getSubject();
            logger.info("User ID: " + userId);

            String email = payload.getEmail();
            String pictureUrl = (String) payload.get("picture");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            logger.info("Checking if the google user already exist in the dataBase ..");
            User checkExistingUser = userService.loadUserByUsername(email);
            if (checkExistingUser != null) {
                logger.info("Google user already exist in the data base");
                return new ResponseEntity<User>(checkExistingUser, HttpStatus.OK);
            }
            else {
                logger.info("Google user don't exist in the data base, porcessing for creating the user ..");
                User createdUser = userService.createUser(new User(familyName, givenName, email, pictureUrl, userId, "this password will never be used"));
                createdUser.setPassword("");
                return new ResponseEntity<User>(createdUser, HttpStatus.OK);
            }
        } else {
            logger.warn("Invalid google ID token.");
            return new ResponseEntity<User>(new User(), HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @PostMapping("/userLogin")
    public ResponseEntity<User> login(@Valid @RequestBody AuthentificationModel auth) throws NoSuchAlgorithmException{
        logger.info("User with email : " + auth.getEmail() + " asked to login ..");
        User user = userService.login(auth.getEmail(), auth.getPassword());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity deleteUser(@Valid @RequestBody DeleteUserModel userDelete, @RequestHeader("Authorization") String token) throws NoSuchAlgorithmException {
        logger.info("Request to delete account with the next authorization " + token);
        logger.info("Processing to delete account with the next token : " + userConfig.extractToken(token));
        return new ResponseEntity((userService.deleteUser(userConfig.extractToken(token), userDelete.getPassword()) == true) ? HttpStatus.OK : HttpStatus.FORBIDDEN);
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws NoSuchAlgorithmException{
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }

}
