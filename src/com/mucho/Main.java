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
    X Write class to get data on a given prospect from basketball reference
    X Compare players to given prospect, check to see if my similar players line up with 538's similar players
    Write code for generating a composite player based on most similar players and their similarity scores
        i.e. weight players with similarity score of 70 more than players with score of 50
    Write code for using comparisons to make projections
    X Figure out some way to automate loading in the stats of a given prospect instead of entering manually
        complete database with more data from 2018-current? scrape off bball ref?

    Add RAPTOR as another statistic/way to compare players
        Make new SQL table for RAPTOR, join on season table where season.name = RAPTOR.name and season.year = RAPTOR.year
        For prospects, get * from RAPTOR where name = Prospect.getName()
    */
    public static void main(String[] args) throws SQLException, IOException {

//        SQLQuerier sql = new SQLQuerier();
//        Player plyr = sql.makePlayer("Damian Lillard");
//        sql.populateCareer(plyr);
//        sql.addRAPTOR(plyr);
//        System.out.println(plyr.getPlayerCareer().getSeasons().get(0).toString());
//        Player plyr2 = sql.makePlayer("Kobe Bryant");
//        sql.populateCareer(plyr2);
//        sql.addRAPTOR(plyr);
        ComparisonTool ct = new ComparisonTool();
        ArrayList<Player> similarPlayers = ct.getSimilarPlayers("Mikal Bridges");
        for (Player plyr : similarPlayers) {
            System.out.println(plyr.toString());
        }
    }
}
