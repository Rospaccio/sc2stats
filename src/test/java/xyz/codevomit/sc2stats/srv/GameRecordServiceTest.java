package xyz.codevomit.sc2stats.srv;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.codevomit.sc2stats.entity.GameOutcome;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.entity.StarcraftRace;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GameRecordServiceTest {

    public static final String USERNAME = "admin";
    public static final String PRINCIPAL = "merka";
    public static final String OPPONENT = "oppo";

    @Autowired
    GameRecordRepository gameRecordRepo;

    @Autowired
    PlayerRepository playerRepo;

    GameRecordService gameRecordSrv;

    @BeforeEach
    public void setUp(){

        gameRecordSrv = new GameRecordService(gameRecordRepo, playerRepo);

        Player principal = new Player();
        principal.setNickname(PRINCIPAL);
        principal.setUsername(USERNAME);

        playerRepo.save(principal);

        Player opponent = new Player();
        opponent.setNickname(OPPONENT);

        playerRepo.save(opponent);
    }

    @AfterEach
    public void clear(){
        playerRepo.deleteAll();
    }

    @Test
    public void testCreateGameRecord(){

        assertNotNull(gameRecordSrv);

        String opponentNickname = "oppo";

        String principalNickname = "merka";

        GameRecord record = new GameRecord();
        record.setOpponentRace(StarcraftRace.ZERG);
        record.setOutcome(GameOutcome.VICTORY);
        record.setPlayedRace(StarcraftRace.PROTOSS);
        record.setGameDate(LocalDate.now());

        GameRecord saved = gameRecordSrv.create(record, USERNAME, principalNickname, opponentNickname);

        assertNotNull(saved.getId());

        GameRecord found = gameRecordRepo.getOne(saved.getId());

        assertNotNull(found.getOpponent());
        assertEquals(opponentNickname, found.getOpponent().getNickname());
        assertEquals(principalNickname, found.getPrincipal().getNickname());
    }
}