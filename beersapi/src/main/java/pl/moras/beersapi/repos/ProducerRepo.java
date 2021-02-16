package pl.moras.beersapi.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.moras.beersapi.models.Producer;

import java.util.Optional;

@Repository
public interface ProducerRepo extends CrudRepository<Producer, Integer> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Producer> findByNameIgnoreCase(String name);
}
