package xyz.codevomit.sc2stats.srv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.codevomit.sc2stats.entity.GameOutcome;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.model.AggregateStatistics;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class StatisticsService {

    GameRecordRepository gameRecordRepo;

    PlayerRepository playerRepo;

    @Autowired
    public StatisticsService(GameRecordRepository grRepo, PlayerRepository pRepo){

        this.gameRecordRepo = grRepo;
        this.playerRepo = pRepo;
    }


    public AggregateStatistics findGlobalStats(String username, String playerNickname) {

        List<GameRecord> allGames = gameRecordRepo.findByPrincipalUsernameAndPrincipalNickname(username, playerNickname);

        int win = 0, loss = 0;

        for(GameRecord game: allGames){
            if(game.getOutcome() == GameOutcome.VICTORY){
                win++;
            }
            else{
                loss++;
            }
        }

        AggregateStatistics stats = new AggregateStatistics();
        stats.setVictoryCount(win);
        stats.setDefeatCount(loss);

        return stats;
    }
}
