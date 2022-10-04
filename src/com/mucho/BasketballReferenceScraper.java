package com.mucho;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class BasketballReferenceScraper {

    public static Player makePlayer(String name) throws IOException, SQLException {
        Player prospectPlayer = new Player(name);
        String[] nameArray = prospectPlayer.getPlayerName().split(" ");
        String lastName = nameArray[1].toLowerCase();
        String shortenedLastName = lastName.substring(0, Math.min(5, lastName.length()));
        String firstName = nameArray[0].toLowerCase();
        String shortenedFirstName = firstName.substring(0, 2);
        String URL = "https://www.basketball-reference.com/players/" + lastName.charAt(0) + "/" + shortenedLastName + shortenedFirstName + "01.html";
        Document doc = Jsoup.connect(URL).get();
        getSeasons(doc, prospectPlayer);
        getPhysicals(doc, prospectPlayer);
        SQLQuerier querier = new SQLQuerier();
        querier.addProspectRAPTOR(prospectPlayer);
        System.out.println(prospectPlayer.getRAPTORAllSeasons());
        return prospectPlayer;
    }

    // since the "advanced" table doesn't show free throw percentage, we have to get it from somewhere else on the page
    // going to create a for each loop that goes through each of the player's season, setting the free throw value
    public static void getFreeThrowPercentage(Document doc, Player plyr) {
//        Element content = doc.getElementById("switcher_per_game-playoffs_per_game");
        Element tables = doc.getElementById("div_per_game");
        StringBuilder sb = new StringBuilder();
        for (Element elmt : tables.getAllElements()) {
            if (elmt.id().contains("per_game")) {
                sb.append(elmt.text() + "\n");
            }
        }
        int counter = 0;
        Scanner sc = new Scanner(sb.toString());
        while (sc.hasNextLine()) {
            String[] lineSplit = sc.nextLine().split(" ");
            if (counter < 2) { // if it's the first two lines of the table (the headers), skip over it
                counter++;
                sc.nextLine();
            } else { // to create a season, we grab the values in the appropriate fields for each line of the "advanced" statistics and set the season's values appropriately
                double freeThrowPercentage = Double.parseDouble(lineSplit[20]);
                plyr.getPlayerCareer().getSeasons().get(counter - 2).setFreeThrowPercentage(freeThrowPercentage);
                // little bit of code soup above, essentially this functions like a for loop with i = counter
                // this bit of code goes through each season (since the final value of counter will be equal to # of seasons + 2), setting FT% for each
                counter++;
            }
        }
    }

        // finds the advanced stats table for the player and creates a season for each row, then adds each new season to the player's career
        public static void getSeasons (Document doc, Player plyr) throws IOException {
//            Element content = doc.getElementById("switcher_advanced-playoffs_advanced");
            Element tables = doc.getElementById("div_advanced");
            StringBuilder sb = new StringBuilder();
            for (Element elmt : tables.getAllElements()) {
                if (elmt.id().contains("advanced")) {
                    sb.append(elmt.text() + "\n");
                }
            }
            int counter = 0;
            Scanner sc = new Scanner(sb.toString());
            while (sc.hasNextLine()) {
                String[] lineSplit = sc.nextLine().split(" ");
                if (counter < 2) { // if it's the first two lines of the table (the headers), skip over it
                    counter++;
                    // the code below is deceivingly long, since it's essentially the same code for the last two else statements, except the first one grabs the player's age and sets it as the player's rookie age
                } else if (counter == 2) { // if the line is the very first season, use its age for the rookieYearAge of the prospect (plyr)

                    plyr.setRookieYearAge(Integer.parseInt(lineSplit[1]));
                    int seasonYear = Integer.parseInt(lineSplit[0].split("-")[1]); // this line's a bit complex, essentially we split "XXXX-XX" into two pieces and take the second part, using it as the season's year
                    Season newSeason = new Season(plyr.getPlayerName(), seasonYear, Integer.parseInt(lineSplit[1]));
                    newSeason.setTrueShooting(Double.parseDouble(lineSplit[8]));
                    newSeason.setUsage(Double.parseDouble(lineSplit[18]));
                    newSeason.setThreePointRate(Double.parseDouble(lineSplit[9]));
                    newSeason.setFreeThrowRate(Double.parseDouble(lineSplit[10]));
                    newSeason.setAssistPercentage(Double.parseDouble(lineSplit[14]));
                    newSeason.setTurnoverPercentage(Double.parseDouble(lineSplit[17]));
                    newSeason.setReboundPercentage(Double.parseDouble(lineSplit[13]));
                    newSeason.setBlockPercentage(Double.parseDouble(lineSplit[16]));
                    newSeason.setStealPercentage(Double.parseDouble(lineSplit[15]));
                    plyr.addSeasonToCareer(newSeason);
                    counter++;

                } else { // to create a season, we grab the values in the appropriate fields for each line of the "advanced" statistics and set the season's values appropriately

                    int seasonYear = Integer.parseInt(lineSplit[0].split("-")[1]);
                    Season newSeason = new Season(plyr.getPlayerName(), seasonYear, Integer.parseInt(lineSplit[1]));
                    newSeason.setTrueShooting(Double.parseDouble(lineSplit[8]));
                    newSeason.setUsage(Double.parseDouble(lineSplit[18]));
                    newSeason.setThreePointRate(Double.parseDouble(lineSplit[9]));
                    newSeason.setFreeThrowRate(Double.parseDouble(lineSplit[10]));
                    newSeason.setAssistPercentage(Double.parseDouble(lineSplit[14]));
                    newSeason.setTurnoverPercentage(Double.parseDouble(lineSplit[17]));
                    newSeason.setReboundPercentage(Double.parseDouble(lineSplit[13]));
                    newSeason.setBlockPercentage(Double.parseDouble(lineSplit[16]));
                    newSeason.setStealPercentage(Double.parseDouble(lineSplit[15]));
                    plyr.addSeasonToCareer(newSeason);

                }
            }
            getFreeThrowPercentage(doc, plyr);
        }

        // looks for the height and weight fields in the HTML code, then sets the height and weight variables accordingly and calls setWeight and setHeight on player
        public static void getPhysicals (Document doc, Player plyr){
            Element content = doc.getElementById("info");
            Element physicalElement = content.getElementById("meta");
            int height = 0;
            int weight = 0;
            for (Element elmt : content.getAllElements()) {
                if (elmt.select("span[itemprop=height]").text().contains("-")) {
                    String[] heightArray = elmt.select("span[itemprop=height]").text().split("-");
                    int feet = Integer.parseInt(heightArray[0]);
                    int inches = Integer.parseInt(heightArray[1]);
                    int heightInInches = (feet * 12) + inches;
                    height = heightInInches;
                }
                if (elmt.select("span[itemprop=weight]").text().contains("lb")) {
                    weight = Integer.parseInt(elmt.select("span[itemprop=weight]").text().substring(0, 3));
                }
            }
            plyr.setHeight(height);
            plyr.setWeight(weight);
        }
    }


