package E_Commerce_Spring.controller;

import E_Commerce_Spring.dto.request.CategoryDtoRequest;
import E_Commerce_Spring.dto.response.CategoryDtoResponse;
import E_Commerce_Spring.exception.StandardError;
import E_Commerce_Spring.model.Category;
import E_Commerce_Spring.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Category Controller", description = "Controlador de funções relacionadas as categorias")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Busca uma lista de dados de todas as categorias")
    @ApiResponse(responseCode = "200", description = "Lista de categorias buscada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){

        List<Category> list = categoryService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Busca os dados de uma categoria pelo Id passado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }

    @Operation(summary = "Cria uma nova categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Categoria criada",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDtoResponse.class))),
            @ApiResponse(responseCode = "400",description = "Validação dos dados ativa",
                content = @Content(mediaType = "applcation/json", schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "409", description = "Categoria já cadastrada",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @PostMapping
    public ResponseEntity<CategoryDtoResponse> createCategory(@RequestBody @Valid CategoryDtoRequest categoryDtoRequest){

        CategoryDtoResponse category = categoryService.createCategory(categoryDtoRequest);

        return ResponseEntity.created(getURI(category.getId())).body(category);
    }

    private URI getURI(Object id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/categories").buildAndExpand(id).toUri();
    }
}
