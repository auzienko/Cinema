package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.AdministratorService;
import edu.school21.cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/panel/films")
public class Films {
    private final String PAGE_PATH = "/admin/panel/films";
    private AdministratorService administratorService;
    private MovieService movieService;

    @Autowired
    public Films(AdministratorService administratorService, MovieService movieService) {
        this.administratorService = administratorService;
        this.movieService = movieService;
    }

    @GetMapping
    public ModelAndView getPage(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);
        Administrator administrator = administratorService.getFromSession(req.getSession());
        if (administrator == null) {
            modelAndView.addObject("error", "❌ This page for administrators only!");
            return modelAndView;
        }
        List<Movie> movieList = movieService.getAllByAdministratorId(administrator.getId());
        if (movieList.size() > 0) {
            modelAndView.addObject("movieList", movieList);
        }
        return modelAndView;
    }

    @PostMapping
    public ModelAndView postPage(HttpServletRequest req,
                                 @RequestParam("posterFile") MultipartFile posterFile,
                                 @RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("yearOfRelease") String yearOfRelease,
                                 @RequestParam("ageRestrictions") String ageRestrictions)  {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);

        Administrator administrator = administratorService.getFromSession(req.getSession());
        if (administrator == null) {
            modelAndView.addObject("error", "❌ This action for administrators only!");
            return modelAndView;
        }

        if (title == null) {
            modelAndView.addObject("error", "❌ Title can't be empty!");
            return modelAndView;
        }

        if (description == null) {
            modelAndView.addObject("error", "❌ Description can't be empty!");
            return modelAndView;
        }
        Integer yOF = null;
        Integer aR = null;
        try{
            yOF = Integer.parseInt(req.getParameter("yearOfRelease"));
            aR = Integer.parseInt(req.getParameter("ageRestrictions"));
        } catch (Exception e) {
            modelAndView.addObject("error", "❌ Year Of Release and Age Restrictions must be numbers!");
            return modelAndView;
        }

        Poster poster = new Poster(posterFile.getOriginalFilename(), UUID.randomUUID(), administrator);
        Movie movie = new Movie(title, yOF, aR, description, poster, administrator);

        System.out.println(poster);
        System.out.println(movie);
        return modelAndView;
    }
}
