package xyz.codevomit.sc2stats.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.thymeleaf.expression.Aggregates;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.entity.StarcraftRace;
import xyz.codevomit.sc2stats.model.AggregateStatistics;
import xyz.codevomit.sc2stats.model.GameLaneItem;
import xyz.codevomit.sc2stats.srv.GameRecordService;
import xyz.codevomit.sc2stats.srv.PlayerService;
import xyz.codevomit.sc2stats.srv.StatisticsService;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    GameRecordService gameRecordService;

    @Autowired
    StatisticsService statsService;

    @ModelAttribute(name = "username")
    String username() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ModelAttribute("nicknameToLane")
    Map<String, List<GameLaneItem>> gameLaneItems() {

        Map<String, List<GameLaneItem>> map = gameRecordService.findLatestGame(username(), 100);
        return map;
    }

    @ModelAttribute(name = "userPlayers")
    List<Player> userPlayers() {

        return playerRepo.findByUsername(username());
    }

    @ModelAttribute("stats")
    Map<String, Map<String, AggregateStatistics>> stats() {

        Map<String, Map<String, AggregateStatistics>> nicknameToStatsMap = new HashMap<>();

        List<Player> players = userPlayers();
        for (Player aPlayer : players) {

            Map<String, AggregateStatistics> forPlayerAndRace = new HashMap<>();
            forPlayerAndRace.put("global", statsService.findGlobalStats(username(), aPlayer.getNickname()));
//            forPlayerAndRace.put("vsTerran", statsService.findStatsVsRace(username(), aPlayer.getNickname(), StarcraftRace.TERRAN);
//            forPlayerAndRace.put("vsTerran", statsService.findStatsVsRace(username(), aPlayer.getNickname(), StarcraftRace.ZERG);
//            forPlayerAndRace.put("vsTerran", statsService.findStatsVsRace(username(), aPlayer.getNickname(), StarcraftRace.PROTOSS);

            nicknameToStatsMap.put(aPlayer.getNickname(), forPlayerAndRace);
        }

        return nicknameToStatsMap;
    }

    @GetMapping("/home")
    public String homepage() {

        return "home";
    }
}
