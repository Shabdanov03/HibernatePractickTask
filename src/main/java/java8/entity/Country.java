package java8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Shabdanov Ilim
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "addresses")
@Entity
@Table(name = "countries")

public class Country {
    @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "countries_id_generators")
@SequenceGenerator(name = "countries_id_generators",
        sequenceName = "countries_seq",
        allocationSize = 1)
    private Long id;
    private String country ;
    private java.lang.String description;
    @OneToMany(mappedBy = "country_id",
            cascade = {
                    CascadeType.REFRESH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<Address> addresses;

    public Country(String country,String description) {
        this.country = country;
        this.description = description;
    }
}
