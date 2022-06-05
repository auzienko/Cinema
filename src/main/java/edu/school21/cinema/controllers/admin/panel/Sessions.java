package edu.school21.cinema.controllers.admin.panel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/panel/sessions")
public class Sessions {
    @GetMapping
    public String getPage(){
        return "/admin/panel/sessions";
    }
}
