package xyz.codevomit.sc2stats.model;

import lombok.Data;
import xyz.codevomit.sc2stats.entity.GameOutcome;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.StarcraftRace;

import java.util.HashMap;
import java.util.Map;

@Data
public class GameLaneItem {

    GameRecord gameRecord;
    Map<StarcraftRace, GameLaneCell> cells = new HashMap<>(3);

    public GameLaneItem(GameRecord record){

        this.gameRecord = record;

        for(StarcraftRace race : StarcraftRace.values()){

            GameLaneCell cell = new GameLaneCell();

            cell.victory = gameRecord.getOpponentRace() == race
                    && gameRecord.getOutcome() == GameOutcome.VICTORY;

            cell.disabled = gameRecord.getOpponentRace() != race;

            cells.put(race, cell);
        }
    }
}
