package edu.school21.cinema.controllers.admin;

import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.models.UserAuthHistory;
import edu.school21.cinema.services.AdministratorService;
import edu.school21.cinema.services.UserAuthHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/admin/signin")
public class SignIn {
    private AdministratorService administratorService;
    private UserAuthHistoryService userAuthHistoryService;

    @Autowired
    public SignIn(AdministratorService administratorService, UserAuthHistoryService userAuthHistoryService) {
        this.administratorService = administratorService;
        this.userAuthHistoryService = userAuthHistoryService;
    }
    @GetMapping
    public ModelAndView getPage() {
        return new ModelAndView("/admin/signin");
    }

    @PostMapping
    public ModelAndView postPage(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<Administrator> user = administratorService.signIn(email, password);
        ModelAndView mv = new ModelAndView();
        if (user.isPresent()) {
            mv.setViewName("redirect:/admin/panel");
            AdministratorService.setToSession(req.getSession(), user.get());
            userAuthHistoryService.save(new UserAuthHistory(LocalDateTime.now(), req.getRemoteAddr(), user.get()));
        } else {
            mv.addObject("error", "Wrong email or password!");
            mv.setViewName("/admin/signin");
        }
        return mv;
    }
}
