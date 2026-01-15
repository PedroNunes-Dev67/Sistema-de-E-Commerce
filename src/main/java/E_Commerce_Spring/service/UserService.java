package E_Commerce_Spring.service;

import E_Commerce_Spring.dto.LoginDto;
import E_Commerce_Spring.dto.TokenDto;
import E_Commerce_Spring.dto.UserDtoRequest;
import E_Commerce_Spring.dto.UserDtoResponse;
import E_Commerce_Spring.exception.ConflictUserResource;
import E_Commerce_Spring.model.User;
import E_Commerce_Spring.model.enums.UserRole;
import E_Commerce_Spring.repository.UserRepository;
import E_Commerce_Spring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public List<UserDtoResponse> findAll(){

        List<User> listUser = userRepository.findAll();

        List<UserDtoResponse> listResponse = listUser.stream()
                .map(user -> {
                    return new UserDtoResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getPhone()
                    );
                }).toList();

        return listResponse;
    }

    @Transactional(readOnly = true)
    public UserDtoResponse findById(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return new UserDtoResponse(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }

    @Transactional
    public UserDtoResponse cadastro(UserDtoRequest userDtoRequest){

        if (userRepository.findByEmail(userDtoRequest.getEmail()).isPresent()) throw new ConflictUserResource("Usuário já cadastrado");

        String passwordCrypt = passwordEncoder.encode(userDtoRequest.getPassword());

        User user = new User(userDtoRequest.getName(), userDtoRequest.getEmail(), userDtoRequest.getPhone(), passwordCrypt);

        user.getRoles().add(UserRole.ROLE_USER);

        userRepository.save(user);

        return new UserDtoResponse(user.getId(),user.getName(),user.getEmail(),user.getPhone());
    }

    @Transactional
    public TokenDto login(LoginDto loginDto){

        String token = authService.authenticateUser(loginDto);

        return new TokenDto(token);
    }

    @Transactional
    public void delete(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        userRepository.delete(user);
    }

    @Transactional
    public UserDtoResponse update(Long id, UserDtoRequest userDtoRequest){

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        verificacaoDadosUser(user, userDtoRequest);

        user = updateData(user, userDtoRequest);

        return new UserDtoResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    private User updateData(User user, UserDtoRequest userDtoRequest ){

        user.setPassword(userDtoRequest.getPassword());

        return userRepository.save(user);
    }

    private void verificacaoDadosUser(User user, UserDtoRequest userDtoRequest){

        if (user.getPassword().equals(userDtoRequest.getPassword())){
            throw new ConflictUserResource("Senha deve ser diferente da anterior");
        }
    }
}
