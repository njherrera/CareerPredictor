package com.mucho;

// compares given player to historical players
// if similarity of historical player is >X, add that player to list of players similar to given player

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ComparisonTool {


//  GET SIMILAR PLAYERS METHOD
//    input: prospect player, list of historical players
//        for each historical player in same age range as prospect player:
//           if checkSimilarity (prospect, historical player) >= some threshold, add the historical player to prospect's similarPlayers


    public ArrayList<Player> getSimilarPlayers(String prospectName) throws SQLException, IOException {
        Player prospect = BasketballReferenceScraper.makePlayer(prospectName);
        prospect.getPlayerCareer().chartGrowth();
        SQLQuerier querier = new SQLQuerier();
        ArrayList<Player> sameAgePlayers = querier.getPlayersSameAge(prospect.getRookieYearAge());
        for (Player plyr : sameAgePlayers) {
            querier.populateCareer(plyr);
            if (plyr.getPlayerCareer().getSeasons().size() > 1) {
                plyr.getPlayerCareer().chartGrowth();
                double similarityScore = checkSimilarity(prospect, plyr);
                prospect.addSimilarPlayer(plyr);
                plyr.setSimilarityScore(similarityScore);
            }
        }
        addRAPTOR(prospect.getSimilarPlayers());
        Collections.sort(prospect.getSimilarPlayers(), new SimilarPlayerComparator());
        return prospect.getSimilarPlayers();
    }

    public void addRAPTOR(ArrayList<Player> plyrs) throws SQLException {
        SQLQuerier querier = new SQLQuerier();
        Player.getNamedPlayer("Luc Mbah a Moute", plyrs);
        for (Player plyr : plyrs) {
            querier.addRAPTOR(plyr);
        }
    }

// add comparison based on RAPTOR - same idea as performance and growth where we're tracking from year to year, except it's just RAPTOR
// this comparison gives us a way to compare players based on their overall impact, which we were lacking before
    public double checkSimilarity(Player prospect, Player historical){
        double overallSimilarity = (comparePerformance(prospect, historical) + compareGrowth(prospect, historical) + comparePhysicals(prospect, historical) + compareRAPTOR(prospect, historical)) / 4;
        return overallSimilarity;
    }




//   using prospect.getPlayerCareer().size() for the loop will result in only comparing the prospect's seasons to the first X of historical player's, with X being the amount of seasons the prospect has played
//   code in for loop progresses through each season in prospect's career and compares to corresponding season in historical player's career
    public double comparePerformance(Player prospect, Player historical){
        double totalSimilarity = 0;
        int shortestCareer = Math.min(prospect.getPlayerCareer().getSeasons().size(), historical.getPlayerCareer().getSeasons().size());
        for (int i = 0; i < shortestCareer; i++) {
           totalSimilarity += prospect.getPlayerCareer().getSeasons().get(i).compareToAnotherSeason(historical.getPlayerCareer().getSeasons().get(i));
        }
        double similarity = totalSimilarity / shortestCareer;
        return similarity;
    }

    public double compareRAPTOR(Player prospect, Player historical){
        double totalSimilarity = 0;
        int shortestCareer = Math.min(prospect.getPlayerCareer().getSeasons().size(), historical.getPlayerCareer().getSeasons().size());
        int seasonCounter = 0;
        for (int i = 0; i < shortestCareer - 1; i++) {
            totalSimilarity += prospect.getPlayerCareer().getSeasons().get(i).compareAnotherSeasonRAPTOR(historical.getPlayerCareer().getSeasons().get(i));
            seasonCounter++;
        }
        double similarity = totalSimilarity / shortestCareer;
        return similarity;
    }


    public double compareGrowth(Player prospect, Player historical){
        double similarity = prospect.getPlayerCareer().compareToAnotherCareer(historical.getPlayerCareer());
        return similarity;
    }



    // pretty straightforward here\
    // the only complicated thing going on is that in order to get an accurate percentage, I use Math.min and Math.max so that the smaller number is always divided by larger number
    // i also subtract 62 from the player's height, since the shortest player ever was 63 inches tall (essentially treating the shortest recorded height as 1)
    public double comparePhysicals(Player prospect, Player historical){
         double biggerHeight = (Math.max(prospect.getHeight(), historical.getHeight())) - 62;
         double smallerHeight = (Math.min(prospect.getHeight(), historical.getHeight()) - 62);
         double heightSimilarity = smallerHeight / biggerHeight;

         double biggerWeight = Math.max(prospect.getWeight(), historical.getWeight());
         double smallerWeight = Math.min(prospect.getWeight(), historical.getWeight());
         double weightSimilarity = smallerWeight / biggerWeight;

         double similarity = (heightSimilarity + weightSimilarity) / 2;
         return similarity;
    }



}
