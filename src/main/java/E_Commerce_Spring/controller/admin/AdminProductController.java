package E_Commerce_Spring.controller.admin;

import E_Commerce_Spring.service.ProductService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
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
