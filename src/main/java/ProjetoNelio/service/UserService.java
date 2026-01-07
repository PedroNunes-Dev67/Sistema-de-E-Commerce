package ProjetoNelio.service;

import ProjetoNelio.dto.UserDtoRequest;
import ProjetoNelio.dto.UserDtoResponse;
import ProjetoNelio.exception.ConflictUserResource;
import ProjetoNelio.model.User;
import ProjetoNelio.repository.UserRepository;
import ProjetoNelio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public UserDtoResponse findById(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return new UserDtoResponse(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }

    public UserDtoResponse insert(UserDtoRequest userDtoRequest){

        if (userRepository.findByEmail(userDtoRequest.getEmail()).isPresent()){
            throw new ConflictUserResource("Usuário já cadastrado");
        }

        User user = new User(userDtoRequest.getName(), userDtoRequest.getEmail(), userDtoRequest.getPhone(), userDtoRequest.getPassword());

        userRepository.save(user);

        return new UserDtoResponse(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }

    public void delete(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        userRepository.delete(user);
    }

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
