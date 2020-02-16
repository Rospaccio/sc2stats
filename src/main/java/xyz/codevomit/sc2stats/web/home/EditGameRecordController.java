package xyz.codevomit.sc2stats.web.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;

import java.util.Optional;

@Slf4j
@Controller
public class EditGameRecordController {

    @Autowired
    GameRecordRepository gameRecordRepo;

    @ModelAttribute("gameRecord")
    public GameRecord gameRecord(@RequestParam("gameRecordId") Long gameRecordId){
        GameRecord found = gameRecordRepo.findById(gameRecordId)
                .orElseThrow(() -> new RuntimeException("Game record not found: " + gameRecordId));
        return found;
    }

    @GetMapping("/edit-game-record")
    public String editGameRecord(@RequestParam("gameRecordId") Long gameRecordId,
                                 Model model){

        model.addAttribute("gameRecordId", gameRecordId);
        return "edit-game-record";
    }

    @PostMapping("/edit-game-record")
    public RedirectView save(@ModelAttribute("gameRecord") GameRecord gameRecord){

        log.info("The GameRecord: {}", gameRecord);

        gameRecordRepo.save(gameRecord);

        return new RedirectView("/home");
    }
}
