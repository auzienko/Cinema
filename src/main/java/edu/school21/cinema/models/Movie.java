package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cinema.movies")
public class Movie extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "year_of_release")
    private Integer yearOfRelease;

    @Column(name = "age_restriction")
    private Integer ageRestrictions;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "poster_id", referencedColumnName = "id")
    private Poster poster;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private Administrator administrator;

}
