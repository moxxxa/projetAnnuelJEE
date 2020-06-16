package esgi.clicfootbackend.clicfootbackend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.Base64;

@Configuration
@ManagedResource
public class UserConfig {

    public String extractToken(String token) {
            return token.substring(token.indexOf(" ") + 1);

    }
/*    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}