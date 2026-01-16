package E_Commerce_Spring.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class CategoryDtoRequest implements Serializable {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
