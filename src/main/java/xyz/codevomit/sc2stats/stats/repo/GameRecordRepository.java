package xyz.codevomit.sc2stats.stats.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.codevomit.sc2stats.entity.GameRecord;

public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {
}
