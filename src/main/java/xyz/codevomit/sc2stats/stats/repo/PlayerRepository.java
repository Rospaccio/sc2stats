package xyz.codevomit.sc2stats.stats.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.codevomit.sc2stats.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByUsername(String username);

    Optional<Player> findByNickname(String opponentNickname);
}
