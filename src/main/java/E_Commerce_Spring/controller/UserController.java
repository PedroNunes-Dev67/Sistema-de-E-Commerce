package E_Commerce_Spring.controller;

import E_Commerce_Spring.dto.LoginDto;
import E_Commerce_Spring.dto.TokenDto;
import E_Commerce_Spring.dto.UserDtoRequest;
import E_Commerce_Spring.dto.UserDtoResponse;
import E_Commerce_Spring.exception.StandardError;
import E_Commerce_Spring.service.UserService;
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

@Tag(name = "User Controller", description = "Controlador de funções relacionadas ao usuário")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Busca uma lista com todos os usuários no banco de dados")
    @ApiResponses(
            @ApiResponse(responseCode = "200",description = "Lista de usuários buscada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoResponse.class)))
    )
    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> findAll(){

        List<UserDtoResponse> list = userService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Retorna os dados de um usuário através do Id passado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> findById(@PathVariable Long id){

        UserDtoResponse user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Cadastra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Validação de dados ativada",
                    content = @Content(mediaType = "applicatin/json", schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado",
                    content = @Content(mediaType = "applicatin/json", schema = @Schema(implementation = StandardError.class)))
    })
    @PostMapping("/cadastro")
    public ResponseEntity<UserDtoResponse> cadastro(@RequestBody @Valid UserDtoRequest userDtoRequest){

        UserDtoResponse newUser = userService.cadastro(userDtoRequest);

        return ResponseEntity.created(currentUri(newUser.getId())).body(newUser);
    }

    private static URI currentUri(Object id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto){

        TokenDto token = userService.login(loginDto);

        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Deleta um usuário por Id passado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza os dados do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validação de dados ativada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para atualizar",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "409", description = "Dados do usuário não podem ser iguais ao anterior",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDtoResponse> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UserDtoRequest userDtoRequest){

         UserDtoResponse user = userService.update(id, userDtoRequest);

         return ResponseEntity.ok(user);
    }
}
