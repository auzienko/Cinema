package edu.school21.cinema.services;

import edu.school21.cinema.models.MovieHall;

import java.util.List;
import java.util.Optional;

public interface MovieHallService {
    void add(MovieHall entity);
    List<MovieHall> getAll();

    List<MovieHall> getAllByAdministratorId(Long id);
    Optional<MovieHall> get(Long id);

    Optional<MovieHall> getBySerialNumber(Integer serialNumber);
}
