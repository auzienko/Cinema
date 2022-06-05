package edu.school21.cinema.services;

import edu.school21.cinema.models.Administrator;

import java.util.Optional;

public interface AdministratorService {
    Optional<Administrator> signUp(Administrator entity);
    Optional<Administrator> signIn(String email, String password);
}
