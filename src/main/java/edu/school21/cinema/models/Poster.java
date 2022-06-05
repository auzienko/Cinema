package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="posters")
public class Poster extends BaseEntity{
    @Column(name = "file_name")
    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private Administrator administrator;
}
