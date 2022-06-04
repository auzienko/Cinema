package edu.school21.cinema.controllers.admin.panel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Sessions {
    @GetMapping("/admin/panel/sessions")
    public String getPage(){
        return "/admin/panel/sessions";
    }
}
