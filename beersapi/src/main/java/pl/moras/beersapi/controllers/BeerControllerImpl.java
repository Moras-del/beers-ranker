package pl.moras.beersapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.moras.beersapi.models.Beer;
import pl.moras.beersapi.models.dtos.BeerDto;
import pl.moras.beersapi.services.BeerService;
import pl.moras.beersapi.services.UserService;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/beers")
@AllArgsConstructor
public class BeerControllerImpl implements BeerController{

    private BeerService beerService;
    private UserService userService;

    @Override
    public ResponseEntity addBeer(BeerDto beerDto, String token) {
        int userId = userService.getId(token).get("id");
        Beer beer = beerService.addBeer(beerDto, userId);
        return ResponseEntity.created(URI.create("/beers/"+beer.getId())).body("Beer created");
    }

    @Override
    public Iterable<Beer> findAll() {
        return beerService.findAll();
    }

    @Override
    public Beer getBeer(int id) {
        return beerService.findById(id);
    }

    @Override
    public Beer rateBeer(int id, int rating, String token) {
        int userId = userService.getId(token).get("id");
        return beerService.rateBeer(id, rating, userId);
    }

    @Override
    public Iterable<Beer> findByProducer(String producer) {
        return beerService.findByProducer(producer);
    }

    @Override
    public Iterable<Beer> findByStyle(int styleId) {
        return beerService.findByStyle(styleId);
    }
}
