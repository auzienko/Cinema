package edu.school21.cinema.repositories;

import edu.school21.cinema.models.MovieHall;

import java.util.List;
import java.util.Optional;

public interface MovieHallRepository extends CrudRepository<MovieHall> {
    List<MovieHall> findAllByAdministratorId(Long id);
    Optional<MovieHall> findBySerialNumber(Integer serialNumber);
}
