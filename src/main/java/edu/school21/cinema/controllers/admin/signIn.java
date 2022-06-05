package edu.school21.cinema.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/signin")
public class signIn {
    @GetMapping
    public ModelAndView getPage(){
        return new ModelAndView("/admin/signin");
    }
}
