package xyz.codevomit.sc2stats.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.srv.PlayerService;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

@Controller
public class CreatePlayerController {

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepository playerRepo;

    @GetMapping("/create-player")
    public String loadPage(){

        return "create-player";
    }

    @PostMapping("/create-player")
    public RedirectView create(@RequestParam("nickname") String nickname){

        Player toCreate = new Player();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        toCreate.setUsername(username);
        toCreate.setNickname(nickname);
        playerRepo.save(toCreate);

        return new RedirectView("/home");
    }
}
