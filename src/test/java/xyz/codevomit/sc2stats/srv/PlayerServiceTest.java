package xyz.codevomit.sc2stats.srv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PlayerServiceTest {

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    GameRecordRepository gameRecordRepo;

    PlayerService playerService;

    public PlayerServiceTest(){

        this.playerService = new PlayerService(playerRepo, gameRecordRepo);
    }

    @Test
    public void testSetup(){

        assertNotNull(playerService);
        assertNotNull(playerRepo);
        assertNotNull(gameRecordRepo);
    }
}