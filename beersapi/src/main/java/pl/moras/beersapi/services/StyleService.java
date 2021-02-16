package pl.moras.beersapi.services;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.moras.beersapi.models.Style;
import pl.moras.beersapi.models.dtos.StyleDto;

@Transactional(readOnly = true)
public interface StyleService {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    Style save(StyleDto styleDto);

    Style findById(int styleId);

    Iterable<Style> findAll();
}
