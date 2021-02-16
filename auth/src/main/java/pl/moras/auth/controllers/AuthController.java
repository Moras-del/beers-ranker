package pl.moras.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.moras.auth.models.User;
import pl.moras.auth.models.dtos.UserDto;

import java.util.Map;


public interface AuthController {

    @PostMapping
    ResponseEntity addUser(@RequestBody UserDto userDto);

    @GetMapping("/{id}")
    User findById(@PathVariable int id);

    @GetMapping
    Iterable<User> findAll();

    @PostMapping("/token")
    Map<String, String> retrieveToken(@RequestHeader("Authorization") String basicHeader);

    @GetMapping("/roles")
    Map<String, Iterable<String>> getRoles(Authentication authentication);

    @GetMapping("/id")
    Map<String, Integer> getId(@RequestHeader("Authorization") String bearerHeader);


}
