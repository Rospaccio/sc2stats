package xyz.codevomit.sc2stats.srv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.Player;
import xyz.codevomit.sc2stats.stats.repo.GameRecordRepository;
import xyz.codevomit.sc2stats.stats.repo.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    GameRecordRepository gameRecordRepo;

    public PlayerService(PlayerRepository playerRepo, GameRecordRepository gameRecordRepo){

        this.playerRepo = playerRepo;
        this.gameRecordRepo = gameRecordRepo;
    }


    public List<Player> findPlayersByCurrentUser(){

        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUserDetails.getUsername();
        List<Player> players = playerRepo.findByUsername(username);
        return players;
    }
}
