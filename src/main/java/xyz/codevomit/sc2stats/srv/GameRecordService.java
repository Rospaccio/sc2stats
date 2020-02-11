package xyz.codevomit.sc2stats.srv;

import org.springframework.stereotype.Service;
import xyz.codevomit.sc2stats.entity.GameOutcome;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.entity.StarcraftRace;
import xyz.codevomit.sc2stats.model.GameLaneItem;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameRecordService {

    GameRecordRepository gameRecordRepo;
    PlayerRepository playerRepo;

    public GameRecordService(GameRecordRepository gameRecordRepo,
                             PlayerRepository playerRepo) {

        this.gameRecordRepo = gameRecordRepo;
        this.playerRepo = playerRepo;
    }

    public GameRecord create(String username, String principalNickname, StarcraftRace principalRace,
                             String opponentNickname, StarcraftRace opponentRace,
                             GameOutcome gameOutcome) {

        Player principalPlayer = playerRepo.findByNickname(principalNickname);
        if (principalPlayer == null) {
            throw new RuntimeException("Principal player not found");
        }

        Player existingOpponent = playerRepo.findByNickname(opponentNickname);
        if (existingOpponent == null) {
            existingOpponent = createNewOpponentPlayer(opponentNickname);
        }

        GameRecord gameRecord = new GameRecord();
        gameRecord.setGameDateTime(LocalDateTime.now());
        gameRecord.setPrincipal(principalPlayer);
        gameRecord.setPlayedRace(principalRace);
        gameRecord.setOpponent(existingOpponent);
        gameRecord.setOpponentRace(opponentRace);
        gameRecord.setOutcome(gameOutcome);

        GameRecord saved = gameRecordRepo.save(gameRecord);

        return saved;
    }

    public Player createNewOpponentPlayer(String opponentNickname) {

        Player theNewOpponent = new Player();
        theNewOpponent.setUsername(null);
        theNewOpponent.setNickname(opponentNickname);

        Player savedOpponent = playerRepo.save(theNewOpponent);
        return savedOpponent;
    }
//
//    public static final String FIND_LATEST = "select gr from GameRecord gr" +
//            "   inner join gr.principal as p " +
//            "   inner join gr.opponent as o " +
//            "       where p.username = :username " +
//            "           and ";

    public Map<String, List<GameLaneItem>> findLatestGame(String username, int count) {

        List<Player> userPlayers = playerRepo.findByUsername(username);

        Map<String, List<GameLaneItem>> nicknameToLane = new HashMap<>();

        for(Player player: userPlayers){
            List<GameRecord> games = gameRecordRepo.findByPrincipalUsernameAndPrincipalNickname(username, player.getNickname());
            List<GameLaneItem> items = games.stream()
                    .map(GameLaneItem::new)
                    .collect(Collectors.toList());
            nicknameToLane.put(player.getNickname(), items);
        }

        return nicknameToLane;
    }

}
