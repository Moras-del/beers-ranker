package pl.moras.beersapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.moras.beersapi.models.Style;

@Repository
public interface StyleRepo extends JpaRepository<Style, Integer> {
    boolean existsByName(String name);
}
