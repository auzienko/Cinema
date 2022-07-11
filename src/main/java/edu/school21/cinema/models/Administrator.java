package edu.school21.cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.servlet.http.HttpSession;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cinema.administrators")
@JsonIgnoreProperties({ "password", "avatar" })
public class Administrator extends BaseEntity {
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private Poster avatar;

//    public void toSessionAttributes(HttpSession httpSession){
//        httpSession.setAttribute("id", getId());
//        httpSession.setAttribute("email", getEmail());
//        httpSession.setAttribute("firstName", getFirstName());
//        httpSession.setAttribute("lastName", getLastName());
//        httpSession.setAttribute("phoneNumber", getPhoneNumber());
//        httpSession.setAttribute("avatarId", getAvatarId());
//    }
}
