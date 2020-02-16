package xyz.codevomit.sc2stats.model;

import lombok.Data;

@Data
public class AggregateStatistics {

    int victoryCount;
    int defeatCount;

    public double getWinRate(){

        if(sum() == 0){
            return 0.0;
        }
        return  (double)victoryCount / sum();
    }

    public double getLossRate(){

        if(sum() == 0) {
            return 0.0;
        }
        return (double)defeatCount / sum();
    }

    private int sum(){

        return victoryCount + defeatCount;
    }
}
