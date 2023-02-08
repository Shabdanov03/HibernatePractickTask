package java8.entity;

import jakarta.persistence.*;
import java8.enums.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"projects","location"})
@Entity
@Table(name = "programmers")


/**
 * Shabdanov Ilim
 **/
public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "programmers_id_generators")
    @SequenceGenerator(name = "programmers_id_generators",
            sequenceName = "programmers_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToMany(cascade = {
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REMOVE})
    @JoinTable(name = "project_programmers")
    private List<Project> projects;
    @OneToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST},fetch = FetchType.LAZY)
    private Address location;

    public Programmer(String fullName, String email, LocalDate dateOfBirth, Status status) {
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }
}
