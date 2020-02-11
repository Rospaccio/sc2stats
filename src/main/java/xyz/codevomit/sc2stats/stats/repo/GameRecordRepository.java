package xyz.codevomit.sc2stats.stats.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.Player;

import java.util.List;

public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {

    public List<GameRecord> findByPrincipalUsernameAndPrincipalNickname(String username, String nickname);
}
