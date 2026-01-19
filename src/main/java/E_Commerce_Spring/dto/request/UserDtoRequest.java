package E_Commerce_Spring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDtoRequest {

    @NotBlank(message = "Nome obrigatório")
    private String name;
    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "Telefone obrigatório")
    private String phone;
    @NotBlank(message = "Senha obrigatória")
    private String password;

    public UserDtoRequest(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
