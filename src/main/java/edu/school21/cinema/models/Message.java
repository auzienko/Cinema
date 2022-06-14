package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Administrator autor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    private MessageType messageType;

}
