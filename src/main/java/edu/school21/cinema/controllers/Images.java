package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.PosterService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/images")
public class Images {
    private Environment env;
    PosterService posterService;

    @Autowired
    public Images(Environment env, PosterService posterService) {
        this.env = env;
        this.posterService = posterService;
    }

    @GetMapping("{id}")
    @ResponseBody
    public byte[] getPage(@PathVariable String id) {
        try {
            UUID u;
            if (id.lastIndexOf(".") != -1) {
                String tst = id.substring(0, id.lastIndexOf("."));
                u = UUID.fromString(id.substring(0, id.indexOf(".")));
            } else {
                u = UUID.fromString(id);
            }
            Optional<Poster> avatar = posterService.findByUUID(u);
            return FileUtils.readFileToByteArray(new File(env.getProperty("storage.path") + "/" + id));

        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @GetMapping("/avatar/{id}")
    @ResponseBody
    public void getAvatarPage(@PathVariable String id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Poster> avatar = posterService.findByUUID(UUID.fromString(id));
        if (avatar.isPresent() && avatar.get().getType() == 2) {
            String relativeImagePath = env.getProperty("storage.path") + "/" + id;
            resp.setContentType(avatar.get().getMime());
            ServletOutputStream outStream;
            outStream = resp.getOutputStream();
            FileInputStream fin = new FileInputStream(relativeImagePath);
            BufferedInputStream bin = new BufferedInputStream(fin);
            BufferedOutputStream bout = new BufferedOutputStream(outStream);
            int ch = 0;
            while ((ch = bin.read()) != -1)
                bout.write(ch);
            bin.close();
            fin.close();
            bout.close();
            outStream.close();
        }
    }
}
