package com.mucho;

import java.util.ArrayList;

public class PlayerArchive {

    // holds list of all historical players

    private ArrayList<Player> historicalPlayers = new ArrayList<>();

    public void addPlayer (Player historicalPlayer){
        this.historicalPlayers.add(historicalPlayer);
    }

    public ArrayList<Player> getHistoricalPlayers(){
        return this.historicalPlayers;
    }
}
