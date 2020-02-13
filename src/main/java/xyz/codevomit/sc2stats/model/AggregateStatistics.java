package xyz.codevomit.sc2stats.model;

import lombok.Data;

@Data
public class AggregateStatistics {

    int victoryCount;
    int defeatCount;

    public double getWinRate(){

        return  (double)victoryCount / sum();
    }

    public double getLossRate(){

        return (double)defeatCount / sum();
    }

    private int sum(){

        return victoryCount + defeatCount;
    }
}
