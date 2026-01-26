package E_Commerce_Spring.controller.admin;

import E_Commerce_Spring.dto.response.UserDtoResponse;
import E_Commerce_Spring.exception.StandardError;
import E_Commerce_Spring.security.SecurityConfiguration;
import E_Commerce_Spring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Admin User Controller", description = "Controle das funções de um usuário pelo admin")
@ApiResponse(responseCode = "403", description = "Usuário não autorizado", content = @Content(mediaType = "application/json"))
@RestController
@RequestMapping("/admin/users")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Retorna todos os usuários")
    @ApiResponse(responseCode = "200",description = "Lista retornada",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserDtoResponse.class)))
    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> findAll(){

        List<UserDtoResponse> list = userService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Retorna os dados de um usuário pelo id")
    @ApiResponse(responseCode = "200",description = "Usuario buscado",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserDtoResponse.class)))
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = StandardError.class)))
    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> findById(@PathVariable Long id){

        UserDtoResponse user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }
}
