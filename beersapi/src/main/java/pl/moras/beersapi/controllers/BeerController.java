package pl.moras.beersapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.moras.beersapi.models.Beer;
import pl.moras.beersapi.models.dtos.BeerDto;

import java.security.Principal;

public interface BeerController {

    @PostMapping
    ResponseEntity addBeer(@RequestBody BeerDto beerDto, @RequestHeader("Authorization") String token);

    @GetMapping
    Iterable<Beer> findAll();

    @GetMapping("/{id}")
    Beer getBeer(@PathVariable int id);

    @PutMapping("/{id}")
    Beer rateBeer(@PathVariable int id, @RequestBody int rating, @RequestHeader("Authorization") String token);

    @GetMapping(params = "producer")
    Iterable<Beer> findByProducer(@RequestParam String producer);

    @GetMapping(params = "style")
    Iterable<Beer> findByStyle(@RequestParam int styleId);

}
