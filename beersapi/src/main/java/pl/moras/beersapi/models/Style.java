package pl.moras.beersapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "style")
@NoArgsConstructor
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @Getter
    private String name;

    @OneToMany(mappedBy = "style")
    @JsonBackReference
    private Set<Beer> beers;

    public Style(String name) {
        this.name = name;
    }

    public Set<Beer> getBeers() {
        if (beers == null)
            beers = new HashSet<>();
        return Collections.unmodifiableSet(beers);
    }

    public void addBeer(Beer beer){
        if (beers == null)
            beers = new HashSet<>();
        beers.add(beer);
    }

}
