package E_Commerce_Spring.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class CategoryDtoResponse implements Serializable {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Computers")
    private String name;

    public CategoryDtoResponse(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
