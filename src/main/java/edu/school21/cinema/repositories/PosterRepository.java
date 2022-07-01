package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Poster;

import java.util.List;

public interface PosterRepository extends CrudRepository<Poster>{
    List<Poster> getAllUserAvatars(Long Id);
}
