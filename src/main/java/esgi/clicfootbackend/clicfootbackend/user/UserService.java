package esgi.clicfootbackend.clicfootbackend.user;

import esgi.clicfootbackend.clicfootbackend.error.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

    public User createUser(User user) throws UserAlreadyExistException
    {
        if(emailExist(user.getEmail())){
            throw new UserAlreadyExistException(
                        "There is an account with that email address: " + user.getEmail()
                );
            }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setId(currentUser.getId());
        user.setEmail(currentUser.getEmail());
        if(isNullOrEmpty(user.getPassword())){
            user.setPassword(passwordEncoder.encode(currentUser.getPassword()));
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

    public User deleteUser(User user){
        userRepository.delete(user);
        return user;
    }
}
