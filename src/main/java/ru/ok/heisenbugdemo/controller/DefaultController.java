package ru.ok.heisenbugdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@Controller
public class DefaultController {

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/403";
    }

    @GetMapping(value = "/error", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String error(HttpServletRequest request, HttpServletResponse response) {
        String originalURL = request.getRequestURL() + "?" +
                URLDecoder.decode(request.getQueryString());

        //        XSS VULNERABILITY HERE
        return "<h1>Error procesing page " + originalURL + "</h1>";
    }


}