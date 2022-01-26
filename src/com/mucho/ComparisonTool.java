package com.mucho;

// compares given player to historical players
// if similarity of historical player is >X, add that player to list of players similar to given player

public class ComparisonTool {


//  GET SIMILAR PLAYERS METHOD
//    input: prospect player, list of historical players
//        for each historical player in same age range as prospect player:
//           if checkSimilarity (prospect, historical player) >= some threshold, add the historical player to similarPlayers
//        return similarPlayers
//
//
//    checkSimilarity (Player prospect, Player historical)
//    input: prospect player, historical player
//    checking height, weight, performance in each season, and growth across seasons
//        separate method for each, this method calls the sub-methods and tallies up the similarity score

    public double checkSimilarity(Player prospect, Player historical){
        double overallSimilarity = (comparePerformance(prospect, historical) + compareGrowth(prospect, historical) + comparePhysicals(prospect, historical)) / 3;
        return overallSimilarity;
    }



//   checkPerformance method (Player prospect, Player historical)
//        for each category in season, compare prospect's performance in category to historical player's performance in category
//        calculate for each season, return average of each season's similarity

//   using prospect.getPlayerCareer().size() for the loop will result in only comparing the prospect's seasons to the first X of historical player's, with X being the amount of seasons the prospect has played
//   code in for loop progresses through each season in prospect's career and compares to corresponding season in historical player's career
    public double comparePerformance(Player prospect, Player historical){
        double totalSimilarity = 0;
        for (int i = 0; i < prospect.getPlayerCareer().getSeasons().size(); i++) {
           totalSimilarity += prospect.getPlayerCareer().getSeasons().get(i).compareToAnotherSeason(historical.getPlayerCareer().getSeasons().get(i));
        }
        double similarity = totalSimilarity / prospect.getPlayerCareer().getSeasons().size();
        return similarity;
    }



//   checkGrowth method (Player prospect, Player historical)
//        calculate percent growth in each individual stat from year-to-year
//            i.e. if historical player's FT% increased by 10% between first 2 seasons, 20% between 2nd and 3rd, 15% between 3rd and 4th, composite FT% growth is 15%
//            compare composite growth of historical player in category to prospect's growth in that category
//            now with similarity of each category's growth, return composite similarity

    public double compareGrowth(Player prospect, Player historical){
        double similarity = prospect.getPlayerCareer().compareToAnotherCareer(historical.getPlayerCareer());
        return similarity;
    }



    // pretty straightforward here
    // the only complicated thing going on is that in order to get an accurate percentage, I use Math.min and Math.max so that the smaller number is always divided by larger number
    public double comparePhysicals(Player prospect, Player historical){
         double biggerHeight = Math.max(prospect.getHeight(), historical.getHeight());
         double smallerHeight = Math.min(prospect.getHeight(), historical.getHeight());
         double heightSimilarity = smallerHeight / biggerHeight;

         double biggerWeight = Math.max(prospect.getWeight(), historical.getWeight());
         double smallerWeight = Math.min(prospect.getWeight(), historical.getWeight());
         double weightSimilarity = smallerWeight / biggerWeight;

         double similarity = (heightSimilarity + weightSimilarity) / 2;
         return similarity;
    }


}
