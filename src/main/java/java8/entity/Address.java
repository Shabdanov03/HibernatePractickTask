package java8.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Shabdanov Ilim
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"country_id","location"})
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "addresses_id_generators")
    @SequenceGenerator(name = "addresses_id_generators",
            sequenceName = "addresses_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "region_name")
    private String regionName;
    private String street;
    @Column(name = "home_number")
    private int homeNumber;
    @ManyToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST},fetch = FetchType.LAZY)
    private Country country_id;
    @OneToOne(mappedBy = "location",
            cascade = {
                    CascadeType.REFRESH,
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST})
    private Programmer programmer;

    public Address(String regionName,String street,int homeNumber) {
        this.regionName = regionName;
        this.street = street;
        this.homeNumber = homeNumber;
    }
}
