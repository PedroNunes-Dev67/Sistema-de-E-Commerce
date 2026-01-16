package E_Commerce_Spring.service;

import E_Commerce_Spring.dto.request.LoginDto;
import E_Commerce_Spring.exception.ResourceNotFoundException;
import E_Commerce_Spring.model.User;
import E_Commerce_Spring.repository.UserRepository;
import E_Commerce_Spring.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    public String authenticateUser(LoginDto loginDto){

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        var authentication = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) authentication.getPrincipal());

        return token;
    }

    public User getUserAuthenticate(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("usuário não encontrado"));
    }
}
