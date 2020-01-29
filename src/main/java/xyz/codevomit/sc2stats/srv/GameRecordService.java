package xyz.codevomit.sc2stats.srv;

import org.springframework.stereotype.Service;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

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

    public GameRecord create(GameRecord record, String username, String principalNickname, String opponentNickname) {
        return null;
//        Optional<Player> principalThatMustExist = playerRepo.findByUsernameAndPrincipal_Nickname(username, principalNickname);
//        Player principalPlayer = principalThatMustExist.orElseThrow(() -> new RuntimeException("Principal Player not found: " + principalNickname));
//
//        Optional<Player> opponentThanMustExist = playerRepo.findByNickname(opponentNickname);
//        Player opponentPlayer = opponentThanMustExist.orElseThrow(() -> new RuntimeException("Opponent Player not found: " + opponentNickname));
//
//        record.setPrincipal(principalPlayer);
//        principalPlayer.getGameRecordsAsPrincipal().add(record);
//
//        record.setOpponent(opponentPlayer);
//        opponentPlayer.getGameRecordsAsOpponent().add(record);
//
//        Long id = gameRecordRepo.save(record).getId();
//        playerRepo.save(opponentPlayer);
//        playerRepo.save(principalPlayer);
//
//        return gameRecordRepo.getOne(id);
    }
}
