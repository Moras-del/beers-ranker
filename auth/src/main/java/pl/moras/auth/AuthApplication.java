package pl.moras.auth;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.moras.auth.models.dtos.UserDto;
import pl.moras.auth.services.UserService;

@SpringBootApplication
@AllArgsConstructor
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	UserService userService;

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> userService.addUser(new UserDto("user", "email@wp.pl", "pass"));
	}
}
