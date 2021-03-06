package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.AdministratorService;
import edu.school21.cinema.services.MessageService;
import edu.school21.cinema.services.MovieService;
import edu.school21.cinema.services.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller("adminPanelFilms")
@RequestMapping("/admin/panel/films")
public class Films {
    private final String PAGE_PATH = "/admin/panel/films";
    private MovieService movieService;
    private AdministratorService administratorService;
    private PosterService posterService;
    private MessageService messageService;
    private Environment env;

    @Autowired
    public Films(MovieService movieService,
                 Environment env,
                 PosterService posterService,
                 AdministratorService administratorService,
                 MessageService messageService) {
        this.movieService = movieService;
        this.env = env;
        this.posterService = posterService;
        this.administratorService = administratorService;
        this.messageService = messageService;
    }

    @GetMapping
    public ModelAndView getPage(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);
        Administrator administrator = AdministratorService.getFromSession(req.getSession());
        List<Movie> movieList = movieService.getAll();
        if (movieList.size() > 0) {
            modelAndView.addObject("movieList", movieList);
        }
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView postPage(HttpServletRequest req,
                                 @RequestParam("posterFile") MultipartFile posterFile,
                                 @RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("yearOfRelease") String yearOfRelease,
                                 @RequestParam("ageRestrictions") String ageRestrictions) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);

        Administrator administrator = AdministratorService.getFromSession(req.getSession());
        if (title == null) {
            modelAndView.addObject("error", "??? Title can't be empty!");
            return modelAndView;
        }

        if (description == null) {
            modelAndView.addObject("error", "??? Description can't be empty!");
            return modelAndView;
        }
        Integer yOF = null;
        Integer aR = null;
        try {
            yOF = Integer.parseInt(req.getParameter("yearOfRelease"));
            aR = Integer.parseInt(req.getParameter("ageRestrictions"));
        } catch (Exception e) {
            modelAndView.addObject("error", "??? Year Of Release and Age Restrictions must be numbers!");
            return modelAndView;
        }
        UUID uuid = UUID.randomUUID();
        try {
            byte[] barr = posterFile.getBytes();
            BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(new FileOutputStream(env.getProperty("storage.path") + "/" + uuid.toString()));
            bufferedOutputStream.write(barr);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("error", "??? Can't save poster!");
            return modelAndView;
        }
        Poster poster = new Poster(posterFile.getOriginalFilename(), 1,
                uuid, posterFile.getSize(), posterFile.getContentType(), administrator);
        Movie movie = new Movie(title, yOF, aR, description, poster, administrator);
        movieService.add(movie);
        modelAndView.setViewName("redirect:" + PAGE_PATH);
        return modelAndView;
    }


    @GetMapping(value = "/{id}/chat")
        public ModelAndView getChat(@PathVariable("id") Long id,HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ModelAndView chatMVC = new ModelAndView(PAGE_PATH +  "/chat");
        chatMVC.addObject("movie",  movieService.get(id).get());
        chatMVC.addObject("user", AdministratorService.getFromSession(req.getSession()));
        List<Message> hist = messageService.getHistory(id);
        chatMVC.addObject("history", hist);
        return chatMVC;
    }
}

