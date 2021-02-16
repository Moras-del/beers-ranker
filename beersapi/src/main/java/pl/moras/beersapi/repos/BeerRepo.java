package pl.moras.beersapi.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.moras.beersapi.models.Beer;
import pl.moras.beersapi.models.Producer;
import pl.moras.beersapi.models.Style;

import java.util.Set;

@Repository
public interface BeerRepo extends CrudRepository<Beer, Integer> {

    Iterable<Beer> findByProducer(Producer producer);

    Iterable<Beer> findByStyle(Style style);
}
