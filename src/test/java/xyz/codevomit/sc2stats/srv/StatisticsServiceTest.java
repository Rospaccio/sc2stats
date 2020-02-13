package xyz.codevomit.sc2stats.srv;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import xyz.codevomit.sc2stats.entity.GameOutcome;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.entity.StarcraftRace;
import xyz.codevomit.sc2stats.model.AggregateStatistics;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
public class StatisticsServiceTest {

    public static final String ADMIN = "admin";
    public static final String TEST_PLAYER = "testPlayer";
    public static final String OPPO = "oppo";
    @Autowired
    GameRecordRepository grRepo;

    @Autowired
    PlayerRepository pRepo;

    private Player principal, opponent;

    @BeforeEach
    public void setUp() {

        principal = new Player();
        principal.setUsername(ADMIN);
        principal.setNickname(TEST_PLAYER);

        pRepo.save(principal);

        opponent = new Player();
        opponent.setNickname(OPPO);

        pRepo.save(opponent);
    }

    @AfterEach
    public void tearDown() {

        pRepo.deleteAll();
    }

    @Test
    public void testFindGlobalStats() {

        assertEquals(TEST_PLAYER, pRepo.findByNickname(TEST_PLAYER).getNickname());

        GameRecord first = new GameRecord();
        first.setPrincipal(principal);
        first.setOpponent(opponent);
        first.setOutcome(GameOutcome.VICTORY);
        first.setOpponentRace(StarcraftRace.ZERG);
        first.setPlayedRace(StarcraftRace.PROTOSS);
        first.setGameDateTime(LocalDateTime.now());
        grRepo.save(first);

        GameRecord second = new GameRecord();
        second.setPrincipal(principal);
        second.setOpponent(opponent);
        second.setOutcome(GameOutcome.DEFEAT);
        second.setOpponentRace(StarcraftRace.ZERG);
        second.setPlayedRace(StarcraftRace.PROTOSS);
        second.setGameDateTime(LocalDateTime.now());
        grRepo.save(second);

        StatisticsService service = new StatisticsService(grRepo, pRepo);

        AggregateStatistics global = service.findGlobalStats(ADMIN, TEST_PLAYER);

        assertNotNull(global);
        assertEquals(1, global.getVictoryCount());
        assertEquals(1, global.getDefeatCount());

        double winRate = global.getWinRate();

        assertEquals(.5, winRate, 0.001);

    }

}