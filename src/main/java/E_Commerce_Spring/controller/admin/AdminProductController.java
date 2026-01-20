package E_Commerce_Spring.controller.admin;

import E_Commerce_Spring.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin Product Controller", description = "Controle das funções de um produto pelo admin")
@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(Long id){

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
