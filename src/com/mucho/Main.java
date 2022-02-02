package com.mucho;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    /*
    X Decide on data sets
    X set up MySQL database for players and seasons
    X Write code for communicating with database to get players and their seasons
        X find players with age within 1 year of prospect
        X create player objects for a given query
        X populate each player's career with seasons
            start with one!
    X Write code for comparing players
        X physicals
        X growth
        X individual seasons
    Write class to get data on a given prospect from basketball reference
    Compare players to given prospect, check to see if my similar players line up with 538's similar players
    Write code for generating a composite player based on most similar players and their similarity scores
        i.e. weight players with similarity score of 70 more than players with score of 50
    Write code for using comparisons to make projections
    Figure out some way to automate loading in the stats of a given prospect instead of entering manually
        complete database with more data from 2018-current? scrape off bball ref?
    */
    public static void main(String[] args) throws SQLException, IOException {

/*        SQLQuerier q = new SQLQuerier();
        Player MJ = q.makePlayer("John Stockton");
        Player Clyde = q.makePlayer("Clyde Drexler");
        Player Kerr = q.makePlayer("Kobe Bryant");
        Player BigWillie = q.makePlayer("Willie Anderson");
        Player Rodman = q.makePlayer("Michael Jordan");
        q.populateCareer(MJ);
        q.populateCareer(Clyde);
        q.populateCareer(Kerr);
        q.populateCareer(BigWillie);
        q.populateCareer(Rodman);
        ComparisonTool ct = new ComparisonTool();
        MJ.getPlayerCareer().chartGrowth();
        Clyde.getPlayerCareer().chartGrowth();
        Kerr.getPlayerCareer().chartGrowth();
        BigWillie.getPlayerCareer().chartGrowth();
        Rodman.getPlayerCareer().chartGrowth();
        System.out.println(ct.compareGrowth(Kerr, Rodman));
        System.out.println(ct.comparePerformance(Kerr, Rodman));
        System.out.println(ct.comparePhysicals(Kerr, Rodman));
        System.out.println(ct.checkSimilarity(Kerr, Rodman));*/

        ComparisonTool ct = new ComparisonTool();
        ArrayList<Player> similarPlayers = ct.getSimilarPlayers("Anthony Edwards");
        for (Player plyr : similarPlayers){
            System.out.println(plyr.toString());
        }
    }
}
