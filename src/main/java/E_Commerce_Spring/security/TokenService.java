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

        Algorithm algorithm = Algorithm.HMAC256(secretKey);  //Algoritmo de geração do token

        try {
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail()) //Email do usuário que está sendo gerado o token
                    .withExpiresAt(expireAtGenerate()) //Tempo de expiração de 15 dias a partir do método
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
                    .withIssuer("auth-api")  //Requer esta assinatura no token
                    .build()
                    .verify(token)//Verifica o token se está nos conformes
                    .getSubject();

            return subjectUser; //Retorna o subject deste token, o email do usuário
        }
        catch (JWTVerificationException e){
            return "";
        }
    }

    //Metodo para gerar tempo de expiração de 15 dias do token
    private Instant expireAtGenerate(){
        return Instant.now().plusSeconds(129600);
    }
}
