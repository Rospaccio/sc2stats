package xyz.codevomit.sc2stats.web.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import xyz.codevomit.sc2stats.entity.GameOutcome;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.StarcraftRace;
import xyz.codevomit.sc2stats.srv.GameRecordService;

@Controller
@Slf4j
public class CreateGameRecordController {

    @Autowired
    GameRecordService gameRecordService;

    String nickname;

    @ModelAttribute("opponent")
    String opponent(){

        return null;
    }

    @ModelAttribute("username")
    String username(){

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/create-game-record")
    public String load(
            @RequestParam("nickname") String nickname,
            @Autowired Model model){

        model.addAttribute("nickname", nickname);
        this.nickname = nickname;
        return "create-game-record";
    }

    @PostMapping("/create-game-record")
    public RedirectView createNewGameRecord(
            @RequestParam("playedRace") StarcraftRace playedRace,
            @RequestParam("opponent") String opponent,
            @RequestParam("opponentRace") StarcraftRace opponentRace,
            @RequestParam("outcome") GameOutcome outcome
            ){

        gameRecordService.create(username(), nickname, playedRace, opponent, opponentRace, outcome);

        return new RedirectView("/home");
    }
}
