package xyz.codevomit.sc2stats.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;

import java.util.Optional;

@Controller
public class EditGameRecordController {

    @Autowired
    GameRecordRepository gameRecordRepo;

    @GetMapping("/edit-game-record")
    public String editGameRecord(@RequestParam("gameRecordId") Long gameRecordId,
                                 Model model){

        GameRecord found = gameRecordRepo.findById(gameRecordId)
                .orElseThrow(() -> new RuntimeException("Game record not found: " + gameRecordId));

        model.addAttribute("gameRecord", found);
        return "edit-game-record";
    }

    @PostMapping("/edit-game-record")
    public RedirectView save(){

        return new RedirectView("/home");
    }
}
