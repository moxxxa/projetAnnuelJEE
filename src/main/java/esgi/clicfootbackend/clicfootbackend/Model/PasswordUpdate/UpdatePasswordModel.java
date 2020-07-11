package esgi.clicfootbackend.clicfootbackend.Model.PasswordUpdate;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class UpdatePasswordModel {

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String passwordConfirm;

    @NotNull
    @NotEmpty
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() { return email; }

}
