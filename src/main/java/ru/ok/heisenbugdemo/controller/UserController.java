package ru.ok.heisenbugdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ok.heisenbugdemo.model.Photo;
import ru.ok.heisenbugdemo.model.User;
import ru.ok.heisenbugdemo.repository.PhotoRepository;
import ru.ok.heisenbugdemo.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public UserController(UserRepository userRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    @GetMapping("/")
    public String home(Model model, RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser();

        if (currentUser == null) {
            return "/login";
        } else {
            redirectAttributes.addAttribute("id", currentUser.getId());
            return "redirect:user";
        }
    }

    @GetMapping("/user")
    public String user(@RequestParam("id") long id, Model model) {
        User currentUser = getCurrentUser();
        User user = userRepository.findOne(id);

        if (user == null) {
            return "/error/404";
        }

        if (!canAccess(currentUser, user)) {
            return "/error/403";
        }

        model.addAttribute("user", user);

        return "/user";
    }

    @GetMapping("/photo")
    public String photo(@RequestParam("id") long id, Model model) {
        Photo photo = photoRepository.findOne(id);

        if (photo == null) {
            return "/error/404";
        }

        User author = userRepository.findOne(photo.getAuthorId());

/*      -=-=-=-= IDOR VULNERABILITY HERE =-=-=-=-

        User currentUser = getCurrentUser();

        if (!canAccess(currentUser, author)) {
            return "/error/403";
        }
*/
        model.addAttribute("user", author);
        model.addAttribute("photo", photo);

        return "/photo";
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public void oops(HttpServletRequest request, HttpServletResponse response) {
        String originalURL = request.getRequestURL() + "?" +
                URLDecoder.decode(request.getQueryString());

        response.setContentType("text/html;charset=UTF-8");

        //        XSS VULNERABILITY HERE
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>Error procesing page " + originalURL + "</h1>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean canAccess(@NotNull User currentUser, @NotNull User anotherUser) {
        return currentUser.equals(anotherUser) ||
                currentUser.getFollows().contains(anotherUser);
    }

    private User getCurrentUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
