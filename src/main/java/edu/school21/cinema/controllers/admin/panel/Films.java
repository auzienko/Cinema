package edu.school21.cinema.controllers.admin.panel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Films {

    @GetMapping("/admin/panel/films")
    public String getPage(){
        return "/admin/panel/films";
    }
}
