package ProjetoNelio.repository;

import ProjetoNelio.dto.UserDtoRequest;
import ProjetoNelio.exception.ResourceNotFoundException;
import ProjetoNelio.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Deve retornar usuário quando usuario com email existir no banco")
    void findByEmailTestSucess(){

        UserDtoRequest userDtoRequest = new UserDtoRequest("Pedro","pedro@gmail.com","81991024299","1234");

        //Persisto usuário no banco
        User user = createUser(userDtoRequest);

        //Pego o usuário pelo email cadastrado
        Optional<User> userFind = userRepository.findByEmail("pedro@gmail.com");

        //Valida que existe User no Optinal e que User é igual ao no momento da persistência
        Assertions.assertTrue(userFind.isPresent());
        Assertions.assertEquals(userFind.get(), user);
    }

    @Test
    @DisplayName("Deve retornar um Optional vazio quando não possui usuario com aquele email no banco")
    void findByEmailTestFail(){

        Optional<User> userFind = userRepository.findByEmail("pedro@gmail.com");

        //Valida que está vazio
        Assertions.assertTrue(userFind.isEmpty());
    }

    private User createUser(UserDtoRequest userDtoRequest){

        User newUser = new User(userDtoRequest.getName(), userDtoRequest.getEmail(), userDtoRequest.getPhone(), userDtoRequest.getPassword());

        entityManager.persist(newUser);
        return newUser;
    }
}
