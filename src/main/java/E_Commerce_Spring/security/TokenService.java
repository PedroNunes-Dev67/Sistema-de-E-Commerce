package E_Commerce_Spring.security;

import E_Commerce_Spring.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${security.jwt.secretkey}")
    private String secretKey;

    public String generateToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expireAtGenerate())
                    .sign(algorithm);

            return token;
        }
        catch (JWTCreationException e){
            throw new RuntimeException("Não foi possivel gerar o token");
        }
    }

    public String validateToken(String token){

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            String subjectUser = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

            return subjectUser;
        }
        catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant expireAtGenerate(){
        return Instant.now().plusSeconds(129600);
    }
}
