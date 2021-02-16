package pl.moras.auth.models.dtos;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class UserDto {

    String username;
    String email;
    String password;

}
