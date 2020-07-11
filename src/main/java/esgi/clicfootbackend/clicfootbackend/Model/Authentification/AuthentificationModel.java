package esgi.clicfootbackend.clicfootbackend.Model.Authentification;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class AuthentificationModel {
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
