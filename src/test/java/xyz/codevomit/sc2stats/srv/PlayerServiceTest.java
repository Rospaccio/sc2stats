package xyz.codevomit.sc2stats.srv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

import java.util.List;

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

//    @Test
//    @WithMockUser(value = "admin", password = "admin")
//    public void testFindPlayersByUser(String username){
//
//        SecurityContextHolder.ge
//
//        List<Player> players = playerService.findPlayersByCurrentUser();
//        assertNotNull(players);
//    }
}