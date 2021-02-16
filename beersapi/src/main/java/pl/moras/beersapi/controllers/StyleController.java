package pl.moras.beersapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.moras.beersapi.models.Style;
import pl.moras.beersapi.models.dtos.StyleDto;

public interface StyleController {

    @GetMapping("/{id}")
    Style findById(@PathVariable int id);

    @GetMapping
    Iterable<Style> findAll();

    @PostMapping
    ResponseEntity addStyle(@RequestBody StyleDto styleDto);


}
