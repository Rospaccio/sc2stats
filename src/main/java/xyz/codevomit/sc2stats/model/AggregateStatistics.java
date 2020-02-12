package xyz.codevomit.sc2stats.model;

import lombok.Data;

@Data
public class AggregateStatistics {

    int victoryCount;
    int defeatCount;

    public double winRate(){

        return  (double)victoryCount / sum();
    }

    public double lossRate(){

        return (double)defeatCount / sum();
    }

    private int sum(){

        return victoryCount + defeatCount;
    }
}
