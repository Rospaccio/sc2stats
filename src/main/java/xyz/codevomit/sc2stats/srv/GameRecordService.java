package xyz.codevomit.sc2stats.srv;

import org.springframework.stereotype.Service;
import xyz.codevomit.sc2stats.entity.GameOutcome;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.entity.StarcraftRace;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

import java.time.LocalDateTime;
import java.util.Optional;

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

        Player principalPlayer = playerRepo.findByNickname(principalNickname)
                .orElseThrow(() -> new RuntimeException("Principal player not found: " + principalNickname));

        Player existingOpponent = playerRepo.findByNickname(opponentNickname)
                .orElse(createNewOpponentPlayer(opponentNickname));

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
}
