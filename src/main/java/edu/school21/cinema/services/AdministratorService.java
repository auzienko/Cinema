package edu.school21.cinema.services;

import edu.school21.cinema.models.Administrator;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface AdministratorService {
    Optional<Administrator> signUp(Administrator entity);

    Optional<Administrator> signIn(String email, String password);

    void setToSession(HttpSession httpSession, Administrator administrator);

    Administrator getFromSession(HttpSession httpSession);

    void removeFromSession(HttpSession httpSession);
}
