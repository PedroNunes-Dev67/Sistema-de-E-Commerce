package ProjetoNelio.controller;

import ProjetoNelio.model.Category;
import ProjetoNelio.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Category Controller", description = "Controlador de funções relacionadas as categorias")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){

        List<Category> list = categoryService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }
}
