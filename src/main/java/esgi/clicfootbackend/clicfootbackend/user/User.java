package esgi.clicfootbackend.clicfootbackend.user;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String email;

    private String picture;

    private String googleId;

    // for google authentification
    public User (String name, String lastName, String email, String picture, String googleId, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.picture = picture;
        this.googleId = googleId;
        this.password = password;
    }

    public User() {
    }
}
