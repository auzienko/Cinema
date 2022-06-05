package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Administrator;

import java.util.Optional;

public interface AdministratorRepository extends CrudRepository<Administrator> {
    Optional<Administrator> findByEmail(String email);
}
