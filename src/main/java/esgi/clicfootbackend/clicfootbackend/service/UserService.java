package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Hash.Md5Hash;
import esgi.clicfootbackend.clicfootbackend.error.LoginMistmatchException;
import esgi.clicfootbackend.clicfootbackend.error.UserAlreadyExistException;
import esgi.clicfootbackend.clicfootbackend.Model.User;
import esgi.clicfootbackend.clicfootbackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

import static com.mysql.cj.util.StringUtils.isNullOrEmpty;

@Service
@Slf4j
public class UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Objects.requireNonNull(email);
        User user = userRepository.getUserByEmail(email);
        return user;
    }

    private boolean emailExist(String email){
        return userRepository.getUserByEmail(email) != null;
    }

    public User createUser(User user) throws UserAlreadyExistException, NoSuchAlgorithmException
    {
        if(emailExist(user.getEmail())) {
            throw new UserAlreadyExistException(
                        "There is an account with that email address: " + user.getEmail()
                );
        }

        user.setPassword(Md5Hash.hashThis(user.getPassword()));
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        return userRepository.save(user);
    }

    public boolean findByToken(String token) {
        User user = userRepository.findByToken(token);
        if(user != null){
           return true;
        }
        return false;
    }

    public User login(String email, String password) throws NoSuchAlgorithmException {
        System.out.println("password =" + Md5Hash.hashThis(password));
        User user = userRepository.login(email, Md5Hash.hashThis(password));
        if (user == null) {
            throw new LoginMistmatchException(
                    "the provided mail / password are not correc t" + email
            );
        }
        user.setPassword("");
        return user;
    }

    public User updateUser(User user) throws NoSuchAlgorithmException{
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setId(currentUser.getId());
        user.setEmail(currentUser.getEmail());
        if(isNullOrEmpty(user.getPassword())){
            user.setPassword(Md5Hash.hashThis(currentUser.getPassword()));
        }
        if(isNullOrEmpty(user.getName())){
            user.setName(currentUser.getName());
        }
        if(isNullOrEmpty(user.getLastName())){
            user.setLastName(currentUser.getLastName());
        }
        if(isNullOrEmpty(user.getPicture()) && isNullOrEmpty(currentUser.getPicture())){
            user.setPicture(currentUser.getPicture());
        }
        return userRepository.save(user);
    }

    public User deleteUser(String token){

        User user = userRepository.findByToken(token);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }
}
