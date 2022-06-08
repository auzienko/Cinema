package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.services.AdministratorService;
import edu.school21.cinema.services.MovieHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/admin/panel/halls")
public class Halls {

    private MovieHallService movieHallService;
    private AdministratorService administratorService;

    @Autowired
    public Halls(MovieHallService movieHallService, AdministratorService administratorService) {
        this.movieHallService = movieHallService;
        this.administratorService = administratorService;
    }

    @GetMapping
    public ModelAndView getPage() {
        return new ModelAndView("/admin/panel/halls");
    }

    @PostMapping
    public ModelAndView postPage(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("/admin/panel/halls");
        Integer serialNumber = null;
        Integer seats = null;
        try {
            serialNumber = Integer.parseInt(req.getParameter("serialNumber"));
            seats = Integer.parseInt(req.getParameter("seats"));
        } catch (Exception e) {
            modelAndView.addObject("error", "Serial number and seats must be numbers!");
            return modelAndView;
        }
        Administrator administrator = administratorService.getFromSession(req.getSession());
        if (administrator == null) {
            modelAndView.addObject("error", "This action for administrators only!");
            return modelAndView;
        }
        Optional<MovieHall> optionalMovieHall = movieHallService.getBySerialNumber(serialNumber);
        if (optionalMovieHall.isPresent()) {
            modelAndView.addObject("error", "This movie hall is already exist!");
            return modelAndView;
        } else {
            movieHallService.add(new MovieHall(serialNumber, seats, administrator));
        }
        return modelAndView;
    }
}
