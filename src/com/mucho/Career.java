package com.mucho;

import java.util.ArrayList;

public class Career {
    /*
    holds the seasons of a player's career
    tracks growth/regression across seasons
     */


    private ArrayList<Season> playerCareer;

    public Career() {
        ArrayList<Season> career = new ArrayList<>();
        this.playerCareer = career;
    }

    public void addSeason(Season season){
        this.playerCareer.add(season);
    }
}
