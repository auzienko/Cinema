package edu.school21.cinema.controllers.admin;


import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.AdministratorService;
import edu.school21.cinema.services.PosterService;
import edu.school21.cinema.services.UserAuthHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;


@AllArgsConstructor
@Controller
@RequestMapping("/admin/profile")
public class Profile {
    private final String PAGE_PATH = "/admin/profile";
    private PosterService posterService;
    private AdministratorService administratorService;
    private UserAuthHistoryService userAuthHistoryService;
    private Environment env;


    @GetMapping
    public ModelAndView getPage(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);
        Administrator user = AdministratorService.getFromSession(req.getSession());
        if (user == null) {
            modelAndView.setViewName("redirect: /");
            return modelAndView;
        }
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

    @PostMapping()
    public ModelAndView postChat(HttpServletRequest req,
                                 @RequestParam("avatarFile") MultipartFile avatarFile) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);

        Administrator administrator = AdministratorService.getFromSession(req.getSession());
        UUID uuid = UUID.randomUUID();
        try {
            byte[] barr = avatarFile.getBytes();
            InputStream is = new ByteArrayInputStream(barr);
            BufferedImage newBi = ImageIO.read(is);
            ImageIO.write(newBi, "jpeg", new File(env.getProperty("storage.path") + "/" + uuid.toString()));
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("error", "❌ Can't save avatar!");
            return modelAndView;
        }
        Poster avatar = new Poster(avatarFile.getOriginalFilename(), 2,
                uuid, avatarFile.getSize(), avatarFile.getContentType(), administrator);
        posterService.add(avatar);
        administratorService.addAvatar(avatar.getId(), administrator);
        modelAndView.setViewName("redirect:" + PAGE_PATH);
        return modelAndView;
    }
}
