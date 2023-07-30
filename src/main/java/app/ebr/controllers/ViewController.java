package app.ebr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/signin", method = { RequestMethod.GET })
    public String signin() {
        return "signin";
    }

    @RequestMapping(value = "/signup", method = { RequestMethod.GET })
    public String signup() {
        return "signup";
    }

    @RequestMapping(value = "/payment/{id}", method = { RequestMethod.GET })
    public String payment(@RequestParam(required = true) long id) {
        return "payment";
    }
}
