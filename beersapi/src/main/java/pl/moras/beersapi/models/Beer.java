package pl.moras.beersapi.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "beer")
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @Getter
    private String name;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    @JsonManagedReference
    private Producer producer;

    @Getter
    @JsonProperty(value = "user")
    private int userId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "style_id")
    @JsonManagedReference
    private Style style;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserBeerRated> userBeerRated;

    @Builder(setterPrefix = "with")
    public Beer(String name, Producer producer, Style style, int userId) {
        this.name = name;
        this.producer = producer;
        this.style = style;
        this.userId = userId;
    }

    public void addRating(int userId, int rating){
        if (userBeerRated == null)
            userBeerRated = new HashSet<>();
        userBeerRated.add(new UserBeerRated(this, userId, rating));
    }

    @JsonProperty("rating")
    private float getRating(){
        float sum = userBeerRated.stream()
                .map(item -> item.getRating())
                .reduce(0, Integer::sum);
        return sum/userBeerRated.size();
    }

    public boolean wasRatedBy(int userId) {
        return userBeerRated
                .stream()
                .anyMatch(id->id.getKey().getUserId()==userId);
    }
}
