package E_Commerce_Spring.security;

import E_Commerce_Spring.model.User;
import E_Commerce_Spring.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getHeaderToken(request);

        if (token != null){

            String emailUser = tokenService.validateToken(token);
            UserDetails user = userRepository.findByEmail(emailUser).orElse(null);

            if (user != null){

                var authenticated = UsernamePasswordAuthenticationToken
                        .authenticated(user.getUsername(), null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticated);
            }
        }

        filterChain.doFilter(request,response);
    }

    private String getHeaderToken(HttpServletRequest request){

        String header = request.getHeader("Authorization");

        if (header == null) return null;

        String token = header.replace("Bearer ","");

        return token;
    }
}
