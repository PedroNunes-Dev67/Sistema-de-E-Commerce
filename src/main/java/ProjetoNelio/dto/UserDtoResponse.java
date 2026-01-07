package ProjetoNelio.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDtoResponse {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Pedro Nunes")
    private String name;
    @Schema(example = "pedro@gmail.com")
    private String email;
    @Schema(example = "81999999999")
    private String phone;

    public UserDtoResponse(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
