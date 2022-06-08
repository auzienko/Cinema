package edu.school21.cinema.models;

import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="administrators")
public class Administrator extends BaseEntity{
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
