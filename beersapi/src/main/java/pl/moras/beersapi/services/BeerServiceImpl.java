package pl.moras.beersapi.services;

import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import pl.moras.beersapi.repos.BeerRepo;
import pl.moras.beersapi.models.*;
import pl.moras.beersapi.models.dtos.BeerDto;

@Service
@AllArgsConstructor
public class BeerServiceImpl implements BeerService {

    private BeerRepo beerRepo;
    private ProducerService producerService;
    private StyleService styleService;

    @Override
    public Iterable<Beer> findAll() {
        return beerRepo.findAll();
    }

    @Override
    public Beer addBeer(BeerDto beerDto, int userId) {
        String producerName = beerDto.getProducer();
        int styleId = beerDto.getStyleId();
        Producer producer = producerService.findOrCreate(producerName);
        Style style = styleService.findById(styleId);
        Beer beer = Beer.builder()
                .withName(beerDto.getName())
                .withProducer(producer)
                .withUserId(userId)
                .withStyle(style)
                .build();
        beer.addRating(userId, beerDto.getRate());
        style.addBeer(beer);
        return beerRepo.save(beer);
    }

    @Override
    public Beer rateBeer(int id, int rating, int userId) {
        Beer beer = findById(id);
        if(beer.wasRatedBy(userId))
            throw new IllegalArgumentException("this beer was already rated by that user");
        beer.addRating(userId, rating);
        return beerRepo.save(beer);
    }

    @Override
    public Beer findById(int id) {
        return beerRepo.findById(id).orElseThrow(()->new ObjectNotFoundException(id, "beer"));
    }

    @Override
    public Iterable<Beer> findByProducer(String producerName) {
        Producer producer = producerService.findByName(producerName);
        return producer.getBeers();
    }

    @Override
    public Iterable<Beer> findByStyle(int styleId) {
        Style style = styleService.findById(styleId);
        return beerRepo.findByStyle(style);
    }
}
