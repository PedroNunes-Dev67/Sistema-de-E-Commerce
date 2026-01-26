package E_Commerce_Spring.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class TokenDto implements Serializable {

    @NotBlank
    @Schema(example = "aiooasndfioasndfioasndfoiasodfasoidfoiasdfiosadpiofnasfn")
    private String token;

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
