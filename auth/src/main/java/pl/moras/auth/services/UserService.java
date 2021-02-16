package pl.moras.auth.services;

import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.moras.auth.models.User;
import pl.moras.auth.models.dtos.UserDto;


@Transactional(readOnly = true)
public interface UserService {

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    User addUser(UserDto userDto);

    User findById(int id);

    Iterable<User> findAll();

    String retrieveToken(String basicHeader);

    Iterable<String> getRoles(Authentication authentication);

    int getId(String bearerHeader);
}
