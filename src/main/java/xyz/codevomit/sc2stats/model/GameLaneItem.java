package xyz.codevomit.sc2stats.model;

import lombok.Data;
import xyz.codevomit.sc2stats.entity.GameOutcome;
import xyz.codevomit.sc2stats.entity.GameRecord;
import xyz.codevomit.sc2stats.entity.StarcraftRace;

@Data
public class GameLaneItem {

    GameRecord gameRecord;

    String protoss;
    String terran;
    String zerg;

    public GameLaneItem(GameRecord record){

        this.gameRecord = record;

        String cssClass = "";

        if(record.getOutcome() == GameOutcome.VICTORY) {
            cssClass = "is-success";
        }
        else{
            cssClass = "is-error";
        }

        protoss = record.getOpponentRace() == StarcraftRace.PROTOSS ? cssClass : "is-disabled";
        terran = record.getOpponentRace() == StarcraftRace.TERRAN ? cssClass : "is-disabled";
        zerg = record.getOpponentRace() == StarcraftRace.ZERG ? cssClass : "is-disabled";

    }
}
