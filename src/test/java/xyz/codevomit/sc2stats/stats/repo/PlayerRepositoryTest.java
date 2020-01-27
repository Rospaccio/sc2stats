package xyz.codevomit.sc2stats.stats.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    PlayerRepository repo;

    @Test
    public void testCRUD(){

        assertNotNull(repo);
    }
}