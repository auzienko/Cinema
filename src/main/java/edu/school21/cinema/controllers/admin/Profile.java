package edu.school21.cinema.controllers.admin;


import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.AdministratorService;
import edu.school21.cinema.services.PosterService;
import edu.school21.cinema.services.UserAuthHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@AllArgsConstructor
@Controller
@RequestMapping("/admin/profile")
public class Profile {
    private final String PAGE_PATH = "/admin/profile";
    private PosterService posterService;
    private UserAuthHistoryService userAuthHistoryService;


    @GetMapping
    public ModelAndView getPage(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);
        Administrator user = AdministratorService.getFromSession(req.getSession());
        Poster avatar = user.getAvatar();
        modelAndView.addObject( "user", user);
        modelAndView.addObject("avatarHistory", posterService.getAllAvatars(user.getId()));
        modelAndView.addObject("authHistory", userAuthHistoryService.getUserAuthHistory(user.getId()));
        if (avatar != null){
            modelAndView.addObject("avatar", "../../images/" + avatar.getFileNameUUID());
        }
        else{
            modelAndView.addObject("avatar", "https://html-online.com/editor/images/html-editor.png");
        }
        return modelAndView;
    }
}
