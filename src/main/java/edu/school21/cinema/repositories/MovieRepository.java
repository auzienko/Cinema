package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Movie;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie> {
    List<Movie> findAllByAdministratorId(Long id);
}
