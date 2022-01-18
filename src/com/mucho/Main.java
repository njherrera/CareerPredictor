package com.mucho;

import java.sql.SQLException;

public class Main {

    /*
    X Decide on data sets
    X set up MySQL database for players and seasons
    Write code for communicating with database to get players and their seasons
        X find players with age within 1 year of prospect
    Write code for comparing players
    Write code for generating a composite player based on most similar players and their similarity scores
        i.e. weight players with similarity score of 70 more than players with score of 50
    Write code for using comparisons to make projections
    Select 10 players from dataset that 538 identifies as most similar to given player
    Analyze 10 players, given player
    Run comparison on given player with just those 10 players
    Check comparison vs 538
    Figure out some way to automate loading in the stats of a given prospect instead of entering manually
    */
    public static void main(String[] args) throws SQLException {

        SQLQuerier q = new SQLQuerier();
        q.selectPlayersSameAge(20);
    }
}
