package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Hash.Md5Hash;
import esgi.clicfootbackend.clicfootbackend.Model.PasswordUpdate.UpdatePasswordModel;
import esgi.clicfootbackend.clicfootbackend.Model.User.UserUpdateModel;
import esgi.clicfootbackend.clicfootbackend.error.LoginMistmatchException;
import esgi.clicfootbackend.clicfootbackend.error.UserAlreadyExistException;
import esgi.clicfootbackend.clicfootbackend.Model.User.User;
import esgi.clicfootbackend.clicfootbackend.repositoryDao.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

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

    public String findByToken(String token) {
        User user = userRepository.findByToken(token);
        return (user != null) ? user.getEmail() : "";
    }

    public User login(String email, String password) throws NoSuchAlgorithmException {
        User user = userRepository.login(email, Md5Hash.hashThis(password));
        if (user == null) {
            throw new LoginMistmatchException(
                    "the provided mail / password are not correc t" + email
            );
        }
        user.setPassword("");
        return user;
    }

    public User updateUser(UserUpdateModel changedUser, String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            user.setEmail(changedUser.getEmail());
            user.setName(changedUser.getName());
            user.setLastName(changedUser.getLastName());
        }
        return userRepository.save(user);
    }

    public boolean deleteUser(String token, String password) throws NoSuchAlgorithmException {

        User user = userRepository.findByToken(token);
        if (user != null && Objects.equals(user.getPassword(), Md5Hash.hashThis(password))) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public boolean updateUserPassword(UpdatePasswordModel changedPassword, String token) throws NoSuchAlgorithmException {
        User user = userRepository.findByToken(token);
        if (user != null && Objects.equals(user.getPassword(), Md5Hash.hashThis(changedPassword.getPassword()))) {
            user.setPassword(Md5Hash.hashThis(changedPassword.getPasswordConfirm()));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
