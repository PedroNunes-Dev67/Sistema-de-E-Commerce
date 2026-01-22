package E_Commerce_Spring.controller;

import E_Commerce_Spring.exception.StandardError;
import E_Commerce_Spring.model.Product;
import E_Commerce_Spring.security.SecurityConfiguration;
import E_Commerce_Spring.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Controller", description = "Controlador de funções relacionadas aos produtos")
@RestController
@RequestMapping("/products")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Busca os dados de todos os produtos")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Lista de todas os produtos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    )
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @Operation(summary = "Busca os dados de um produto pelo Id passado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }
}
