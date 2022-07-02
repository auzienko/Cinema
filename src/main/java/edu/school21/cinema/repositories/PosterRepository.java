package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Poster;
import javafx.geometry.Pos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PosterRepository extends CrudRepository<Poster>{
    List<Poster> getAllUserAvatars(Long Id);
    Optional<Poster> getByUUID(UUID UUID);
}
