package pl.moras.beersapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "producer")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.MERGE)
    @JsonBackReference
    private List<Beer> beers;

    public Producer(String name) {
        this.name = name;
    }

    public List<Beer> getBeers(){
        if(beers==null)
            beers = new ArrayList<>();
        return Collections.unmodifiableList(beers);
    }

}
