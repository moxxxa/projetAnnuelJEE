package esgi.clicfootbackend.clicfootbackend.Model;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class DeleteUserModel {
    @NotNull
    @NotEmpty
    private String password;

    public String getPassword() {
        return password;
    }
}
