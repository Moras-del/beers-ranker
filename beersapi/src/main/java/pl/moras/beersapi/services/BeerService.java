package pl.moras.beersapi.services;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.moras.beersapi.models.Beer;
import pl.moras.beersapi.models.dtos.BeerDto;

@Transactional(readOnly = true)
public interface BeerService {

    Iterable<Beer> findAll();

    @Transactional(isolation = Isolation.READ_COMMITTED)
    Beer addBeer(BeerDto beerDto, int userId);

    @Transactional
    Beer rateBeer(int id, int rating, int userId);

    Beer findById(int id);

    Iterable<Beer> findByProducer(String producerName);

    Iterable<Beer> findByStyle(int styleid);

}
