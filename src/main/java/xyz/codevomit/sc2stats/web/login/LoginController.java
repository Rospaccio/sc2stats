package xyz.codevomit.sc2stats.web.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("")
public class LoginController {

    @ModelAttribute("username")
    public String username(){

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }
}
