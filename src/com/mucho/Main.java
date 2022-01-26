package com.mucho;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    /*
    X Decide on data sets
    X set up MySQL database for players and seasons
    Write code for communicating with database to get players and their seasons
        X find players with age within 1 year of prospect
        X create player objects for a given query
        X populate each player's career with seasons
            start with one!
    Write code for comparing players
        X physicals
        growth
        X individual seasons
    Compare players to given prospect, check to see if my similar players line up with 538's similar players
    Write code for generating a composite player based on most similar players and their similarity scores
        i.e. weight players with similarity score of 70 more than players with score of 50
    Write code for using comparisons to make projections
    Figure out some way to automate loading in the stats of a given prospect instead of entering manually
        complete database with more data from 2018-current? scrape off bball ref?
    */
    public static void main(String[] args) throws SQLException {

        SQLQuerier q = new SQLQuerier();
        Player MJ = q.makePlayer("Michael Jordan");
        Player Clyde = q.makePlayer("Clyde Drexler");
        q.populateCareer(MJ);
        q.populateCareer(Clyde);
        ComparisonTool ct = new ComparisonTool();
        MJ.getPlayerCareer().chartGrowth();
        Clyde.getPlayerCareer().chartGrowth();
        ct.compareGrowth(MJ, Clyde);

    }
}
