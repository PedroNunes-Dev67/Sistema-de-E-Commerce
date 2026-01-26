package E_Commerce_Spring.controller.admin;

import E_Commerce_Spring.exception.StandardError;
import E_Commerce_Spring.security.SecurityConfiguration;
import E_Commerce_Spring.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin Product Controller", description = "Controle das funções de um produto pelo admin")
@ApiResponse(responseCode = "403", description = "Usuário não autorizado", content = @Content(mediaType = "application/json"))
@RestController
@RequestMapping("/admin/products")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Deleta um produto pr id")
    @ApiResponse(responseCode = "204",description = "Produto deletado",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404",description = "Produto não encontrado",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = StandardError.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(Long id){

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
