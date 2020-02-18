package xyz.codevomit.sc2stats.model;

import lombok.Data;

@Data
public class GameLaneCell {

    boolean disabled;

    boolean victory;

    public boolean isDefeat(){
        return !victory;
    }

    public void setDefeat(boolean defeat){
        this.victory = !defeat;
    }
}
