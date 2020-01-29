package xyz.codevomit.sc2stats.stats.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.Player;

import java.util.Optional;

public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {
}
