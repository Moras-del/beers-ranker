package pl.moras.beersapi.services;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.moras.beersapi.models.Producer;

public interface ProducerService {

    @Transactional(readOnly = true)
    Iterable<Producer> findAll();

    @Transactional(isolation = Isolation.READ_COMMITTED)
    Producer save(String name);

    @Transactional(readOnly = true)
    Producer findById(int id);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    Producer findOrCreate(String name);

    @Transactional(readOnly = true)
    Producer findByName(String producerName);
}
