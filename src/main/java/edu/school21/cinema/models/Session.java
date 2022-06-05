package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="sessions")
public class Session extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "cost")
    private Integer cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_hall_id", referencedColumnName = "id")
    private MovieHall movieHall;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private Administrator administrator;
}
