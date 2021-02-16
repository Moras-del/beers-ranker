package pl.moras.beersapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.moras.beersapi.models.Style;
import pl.moras.beersapi.models.dtos.StyleDto;
import pl.moras.beersapi.services.StyleService;

import java.net.URI;

@RestController
@RequestMapping("/styles")
@AllArgsConstructor
public class StyleControllerImpl implements StyleController{

    private StyleService styleService;

    @Override
    public Style findById(int id) {
        return styleService.findById(id);
    }

    @Override
    public Iterable<Style> findAll() {
        return styleService.findAll();
    }

    @Override
    public ResponseEntity addStyle(StyleDto styleDto) {
        Style style = styleService.save(styleDto);
        return ResponseEntity.created(URI.create("/styles/"+style.getId())).body("Style created");
    }
}
