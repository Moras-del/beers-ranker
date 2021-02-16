package pl.moras.auth.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.moras.auth.models.User;
import pl.moras.auth.models.dtos.UserDto;
import pl.moras.auth.services.UserService;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {

    private UserService userService;

    @Override
    public ResponseEntity addUser(UserDto userDto) {
        User user = userService.addUser(userDto);
        return ResponseEntity.created(URI.create("/"+user.getId())).body("User created");
    }

    @Override
    public User findById(int id) {
        return userService.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userService.findAll();
    }

    @Override
    public Map<String, String> retrieveToken(String basicHeader) {
        return Collections.singletonMap("token", userService.retrieveToken(basicHeader));
    }

    @Override
    public Map<String, Iterable<String>> getRoles(Authentication authentication) {
        return Collections.singletonMap("roles", userService.getRoles(authentication));
    }

    @Override
    public Map<String, Integer> getId(String bearerHeader) {
        return Collections.singletonMap("id", userService.getId(bearerHeader));
    }
}
