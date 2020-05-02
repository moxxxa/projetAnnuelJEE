package esgi.clicfootbackend.clicfootbackend.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.mysql.cj.util.StringUtils.isNullOrEmpty;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        if(isNullOrEmpty(user.getPassword())){
            user.setPassword(currentUser.getPassword());
        }
        if(isNullOrEmpty(user.getEmail())){
            user.setEmail(currentUser.getEmail());
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
