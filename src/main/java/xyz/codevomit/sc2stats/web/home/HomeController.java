package xyz.codevomit.sc2stats.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.srv.PlayerService;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepository playerRepo;

    @ModelAttribute(name = "username")
    String username(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ModelAttribute(name = "userPlayers")
    List<Player> userPlayers(){

        return playerRepo.findByUsername(username());
    }

    @GetMapping("/home")
    public String homepage() {

        return "home";
    }
}
