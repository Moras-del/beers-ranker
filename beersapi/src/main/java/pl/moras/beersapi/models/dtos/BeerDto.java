package pl.moras.beersapi.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Value
@AllArgsConstructor
public class BeerDto {

    String name;
    int styleId;
    String producer;

    @Min(value = 0)
    @Max(value = 10)
    int rate;

}
