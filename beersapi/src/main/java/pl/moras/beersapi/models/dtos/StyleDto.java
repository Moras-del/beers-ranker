package pl.moras.beersapi.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@AllArgsConstructor
public class StyleDto {

    @NotBlank(message = "name of style can't be blank")
    String name;

}
