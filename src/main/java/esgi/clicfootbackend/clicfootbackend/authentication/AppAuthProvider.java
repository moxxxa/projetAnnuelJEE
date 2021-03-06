package esgi.clicfootbackend.clicfootbackend.authentication;

import esgi.clicfootbackend.clicfootbackend.service.UserService;
import esgi.clicfootbackend.clicfootbackend.Model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AppAuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private Boolean checkCredentials(User user, String password){
//        System.out.println("user password =" + user.getPassword());
        if(passwordEncoder.matches(password, user.getPassword())){
            return false;
        }
        return true;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)authentication;

        String name = auth.getName();
        String password = auth.getCredentials().toString();

        User user = userService.loadUserByUsername(name);
        if(checkCredentials(user, password)){
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }
        else{
            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), new ArrayList<>());
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
