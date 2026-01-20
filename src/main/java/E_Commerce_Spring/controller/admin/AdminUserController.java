package E_Commerce_Spring.controller.admin;

import E_Commerce_Spring.dto.response.UserDtoResponse;
import E_Commerce_Spring.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Admin User Controller", description = "Controle das funções de um usuário pelo admin")
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> findAll(){

        List<UserDtoResponse> list = userService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> findById(@PathVariable Long id){

        UserDtoResponse user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }
}
