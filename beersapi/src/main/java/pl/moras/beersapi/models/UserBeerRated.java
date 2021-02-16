package pl.moras.beersapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import pl.moras.beersapi.models.Beer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_beer_rated")
@NoArgsConstructor
@Data
public class UserBeerRated implements Serializable{

    @EmbeddedId
    private Key key;

    @MapsId("beerId")
    @JoinColumn(name = "beer_id")
    @ManyToOne
    private Beer beer;

    private int rating;

    public UserBeerRated(Beer beer, int userId, int rating) {
        this.beer = beer;
        this.key = new Key(beer.getId(), userId);
        this.rating = rating;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Embeddable
    static class Key implements Serializable {

        @Column(name = "beer_id")
        private int beerId;

        private int userId;

    }

}
