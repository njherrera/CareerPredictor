package com.mucho;

import java.io.IOException;
import java.util.ArrayList;

public class Player {

    private ArrayList<Player> similarPlayers;
    private String playerName;
    private int height;
    private int weight;
    private int rookieYearAge;
    private double similarityScore;
    private Career playerCareer;

    public Player(String name, int height, int weight, int rookieAge){
        this.playerName = name;
        this.height = height;
        this.weight = weight;
        this.rookieYearAge = rookieAge;
        this.playerCareer = new Career();
        this.similarPlayers = new ArrayList<Player>();
    }

    public Player(String name){
        this.playerName = name;
        this.playerCareer = new Career();
        this.similarPlayers = new ArrayList<Player>();
    }

    public void addSimilarPlayer(Player similarPlayer){
        similarPlayers.add(similarPlayer);
    }

    public ArrayList<Player> getSimilarPlayers() {
        return similarPlayers;
    }

    public void setSimilarPlayers(ArrayList<Player> similarPlayers) {
        this.similarPlayers = similarPlayers;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRookieYearAge() {
        return rookieYearAge;
    }

    public void setRookieYearAge(int rookieYearAge) {
        this.rookieYearAge = rookieYearAge;
    }

    public void addSeasonToCareer(Season season){
        this.playerCareer.addSeason(season);
    }
    public Career getPlayerCareer(){
        return playerCareer;
    }

    public void setPlayerCareer(Career career){
        this.playerCareer = career;
    }

    public void setSimilarityScore(double score){
        this.similarityScore = score;
    }

    public double getSimilarityScore(){ return this.similarityScore;}

    // helper method for bug-fixing, not sure where else to put it
    static public void getNamedPlayer(String playerName, ArrayList<Player> players){
        for (Player plyr : players){
            if (plyr.getPlayerName().equals(playerName)){
                System.out.println(plyr.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", rookieYearAge=" + rookieYearAge +
                ", career length=" + playerCareer.getSeasons().size() +
                ", similarity score=" + similarityScore +
                '}';
    }
}
