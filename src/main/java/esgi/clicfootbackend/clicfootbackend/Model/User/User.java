package esgi.clicfootbackend.clicfootbackend.Model.User;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

}
