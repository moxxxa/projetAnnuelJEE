package esgi.clicfootbackend.clicfootbackend.Model.User;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class DeleteUserModel {
    @NotNull
    @NotEmpty
    private String password;

    private String email;

    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }
}
