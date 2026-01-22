package E_Commerce_Spring.controller.admin;

import E_Commerce_Spring.dto.request.CategoryDtoRequest;
import E_Commerce_Spring.dto.response.CategoryDtoResponse;
import E_Commerce_Spring.model.Category;
import E_Commerce_Spring.security.SecurityConfiguration;
import E_Commerce_Spring.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Admin Category Controller", description = "Controle das funções de uma categoria pelo admin")
@RestController
@RequestMapping("/admin/categories")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Lista todas as categorias")
    @ApiResponse(responseCode = "200",description = "Categorias retornadas")
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){

        List<Category> list = categoryService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Busca uma categoria por id")
    @ApiResponse(responseCode = "200",description = "Categoria retornada")
    @ApiResponse(responseCode = "404",description = "Categoria não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }

    @Operation(summary = "Cria uma nova categoria")
    @ApiResponse(responseCode = "201", description = "Categoria criada")
    @ApiResponse(responseCode = " 409", description = "Categoria já cadastrada")
    @PostMapping
    public ResponseEntity<CategoryDtoResponse> createCategory(@RequestBody @Valid CategoryDtoRequest categoryDtoRequest){

        CategoryDtoResponse category = categoryService.createCategory(categoryDtoRequest);

        return ResponseEntity.created(getURI(category.getId())).body(category);
    }

    private URI getURI(Object id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/categories").buildAndExpand(id).toUri();
    }
}
