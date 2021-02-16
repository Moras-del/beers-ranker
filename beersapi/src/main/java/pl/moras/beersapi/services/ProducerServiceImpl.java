package pl.moras.beersapi.services;

import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import pl.moras.beersapi.repos.ProducerRepo;
import pl.moras.beersapi.exceptions.ObjectAlreadyExists;
import pl.moras.beersapi.models.Producer;

@Service
@AllArgsConstructor
public class ProducerServiceImpl implements pl.moras.beersapi.services.ProducerService {

    private ProducerRepo producerRepo;

    @Override
    public Iterable<Producer> findAll() {
        return producerRepo.findAll();
    }

    @Override
    public Producer save(String name) {
        if(producerRepo.existsByNameIgnoreCase(name))
            throw new ObjectAlreadyExists("producer");
        Producer producer = new Producer(name);
        return producerRepo.save(producer);
    }

    @Override
    public Producer findById(int id) {
        return producerRepo.findById(id).orElseThrow(()->new ObjectNotFoundException(id, "producer"));
    }

    @Override
    public Producer findOrCreate(String name) {
        return producerRepo.findByNameIgnoreCase(name).orElseGet(()->save(name));
    }

    @Override
    public Producer findByName(String producerName) {
        return producerRepo.findByNameIgnoreCase(producerName).orElseThrow(()->new ObjectNotFoundException(producerName, "Producer"));
    }
}
