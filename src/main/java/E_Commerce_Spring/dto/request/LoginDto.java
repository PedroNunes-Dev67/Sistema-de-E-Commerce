package E_Commerce_Spring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class LoginDto implements Serializable {

    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inváldio")
    private String email;
    @NotBlank(message = "Senha obrigatória")
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
