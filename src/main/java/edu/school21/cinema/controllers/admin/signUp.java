package edu.school21.cinema.controllers.admin;

import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/admin/signup")
public class signUp {
    private AdministratorService administratorService;

    @Autowired
    public signUp(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping
    public ModelAndView getPage() {
        return new ModelAndView("/admin/signup");
    }

    @PostMapping
    public ModelAndView postPage(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<Administrator> user = administratorService.signUp(new Administrator(email, password));

        return new ModelAndView("/admin/signup");
    }
}
