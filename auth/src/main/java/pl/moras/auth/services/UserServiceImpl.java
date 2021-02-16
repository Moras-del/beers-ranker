package pl.moras.auth.services;

import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.moras.auth.exceptions.ObjectAlreadyExists;
import pl.moras.auth.models.Role;
import pl.moras.auth.models.User;
import pl.moras.auth.models.dtos.UserDto;
import pl.moras.auth.repos.UserRepo;
import pl.moras.auth.security.JwtTokenProvider;

import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Collections;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public User addUser(UserDto userDto) {
        String username = userDto.getUsername();
        if(userRepo.existsByUsername(username))
            throw new ObjectAlreadyExists("user");
        String password = passwordEncoder.encode(userDto.getPassword());
        String email = userDto.getEmail();
        User user = new User(username, email, password);
        user.addRole(new Role("USER"));
        return userRepo.save(user);
    }

    @Override
    public User findById(int id) {
        return userRepo.findById(id).orElseThrow(()->new ObjectNotFoundException(id, "User"));
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public String retrieveToken(String basicHeader) {
        String[] credentials = decode(basicHeader);
        String username = credentials[0];
        String password = credentials[1];
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepo.findByUsername(username);
        return jwtTokenProvider.createToken(username, user.getId(), user.getRoles());
    }

    @Override
    public Iterable<String> getRoles(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(role->role.getAuthority())
                .collect(Collectors.toList());
    }

    @Override
    public int getId(String bearerHeader) {
        return jwtTokenProvider.getId(bearerHeader.substring(7));
    }

    private String[] decode(String authHeader){
        byte[] decoded = Base64.getDecoder().decode(authHeader.substring(6));
        System.out.println(new String(decoded));
        return new String(decoded).split(":");
    }
}
