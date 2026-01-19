package E_Commerce_Spring.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UpdateDtoRequest {

    @NotBlank(message = "Senha obrigatória")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
