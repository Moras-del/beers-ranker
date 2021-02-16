package pl.moras.beersapi.services;

import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import pl.moras.beersapi.exceptions.ObjectAlreadyExists;
import pl.moras.beersapi.models.Style;
import pl.moras.beersapi.models.dtos.StyleDto;
import pl.moras.beersapi.repos.StyleRepo;

@Service
@AllArgsConstructor
public class StyleServiceImpl implements StyleService {

    private StyleRepo styleRepo;

    @Override
    public Style save(StyleDto styleDto) {
        String name = styleDto.getName();
        if (styleRepo.existsByName(name))
            throw new ObjectAlreadyExists(name);
        return styleRepo.save(new Style(name));
    }

    @Override
    public Style findById(int styleId) {
        return styleRepo.findById(styleId).orElseThrow(()->new ObjectNotFoundException(styleId, "Style"));
    }

    @Override
    public Iterable<Style> findAll() {
        return styleRepo.findAll();
    }
}
