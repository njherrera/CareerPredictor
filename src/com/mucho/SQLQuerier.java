package com.mucho;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class SQLQuerier {

    /*
    interacts with the SQL database
    pulls players with similar rookie year age to prospect, then creates Player objects for each of them
    pulls seasons of each queried player, then creates Season objects for each of them
     */

    private Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/nbaplayers", Credentials.getUserName(), Credentials.getPass());
    private Statement statement = null;
    private Statement RAPTORstatement = null;
    private ResultSet rSet = null;
    private ResultSet RAPTORset = null;
    private PreparedStatement preparedStatement = null;

    public SQLQuerier() throws SQLException {
    }

    // automates a query for finding historical players where historical player rookie year age is within 1 year of prospect
    public ArrayList<Player> getPlayersSameAge(int age) throws SQLException {
        ArrayList<Player> sameAgePlayers = new ArrayList<>();
        try{
            statement = connect.createStatement();
            rSet = statement.executeQuery("SELECT * FROM player WHERE ABS(rookieYearAge - " + age + ") <= 1");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } while (rSet.next()){
            Player newPlayer = new Player(rSet.getString("name"), rSet.getInt("height"), rSet.getInt("weight"), rSet.getInt("rookieYearAge"));
            sameAgePlayers.add(newPlayer);
        }
        return sameAgePlayers;
    }

    // mostly a bug-fixing method
    public Player makePlayer(String name) throws SQLException {
        Player newPlayer = null;
        try{
            statement = connect.createStatement();
            rSet = statement.executeQuery("SELECT * FROM player WHERE name = \"" + name + "\" ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } while (rSet.next()){
            newPlayer = new Player(rSet.getString("name"), rSet.getInt("height"), rSet.getInt("weight"), rSet.getInt("rookieYearAge"));
        }
        return newPlayer;
    }

    public void populateCareer(Player plyr) throws SQLException {
        try {
            statement = connect.createStatement();
            rSet = statement.executeQuery("SELECT * FROM season WHERE name = \"" + plyr.getPlayerName() + "\" ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (rSet.next()) {
            Season newSeason = new Season(plyr.getPlayerName(), rSet.getInt("year"), rSet.getInt("age"));
            newSeason.setTrueShooting(rSet.getDouble("trueShooting"));
            newSeason.setFreeThrowPercentage(rSet.getDouble("freeThrowPercentage"));
            newSeason.setUsage(rSet.getDouble("usage"));
            newSeason.setThreePointRate(rSet.getDouble("threePointRate"));
            newSeason.setFreeThrowRate(rSet.getDouble("freeThrowRate"));
            newSeason.setAssistPercentage(rSet.getDouble("assistPercentage"));
            newSeason.setTurnoverPercentage(rSet.getDouble("turnoverPercentage"));
            newSeason.setReboundPercentage(rSet.getDouble("reboundPercentage"));
            newSeason.setBlockPercentage(rSet.getDouble("blockPercentage"));
            newSeason.setStealPercentage(rSet.getDouble("stealPercentage"));
            plyr.addSeasonToCareer(newSeason);
        }
    }
/*
    // using a second SQL query because the RAPTOR data is in another table
    public void addRAPTOR(Player plyr) throws SQLException {
            statement = connect.createStatement();
            rSet = statement.executeQuery("SELECT raptor_total FROM historical_raptor_by_player WHERE player_name = \"" + plyr.getPlayerName() + "\" ");
            int counter = 0;
        while (rSet.next()){
                try {
                    plyr.getPlayerCareer().getSeasons().get(counter).setRAPTOR(rSet.getDouble("raptor_total"));
                    counter++;
                    if (counter > (plyr.getPlayerCareer().getSeasons().size() - 1)) {
                        break; // RAPTOR data is recent, so with current players there are more seasons for each player in the RAPTOR table than in my season table
                    }
                } catch (IndexOutOfBoundsException ex){
                    System.out.println(plyr.getPlayerName());
                }
        }
    }

    public void addProspectRAPTOR(Player plyr) throws SQLException {
        statement = connect.createStatement();
        rSet = statement.executeQuery("SELECT raptor_total FROM historical_raptor_by_player WHERE player_name = \"" + plyr.getPlayerName() + "\" ");
        int counter = 0;
        if (!rSet.isBeforeFirst() ) {
            System.out.println("No data");
        }
        while (rSet.next()){
            try {
                plyr.getPlayerCareer().getSeasons().get(counter).setRAPTOR(rSet.getDouble("raptor_total"));
                counter++;
                if (counter > (plyr.getPlayerCareer().getSeasons().size() - 1)) {
                    break; // RAPTOR data is recent, so with current players there are more seasons for each player in the RAPTOR table than in my season table
                }
            } catch (IndexOutOfBoundsException ex){
                System.out.println(plyr.getPlayerName());
            }
        }
    }*/

    // using a second SQL query because the RAPTOR data is in another table
    public void addRAPTOR(Player plyr) throws SQLException {
            statement = connect.createStatement();
            rSet = statement.executeQuery("SELECT raptor_total FROM historical_raptor_by_player WHERE player_name = \"" + plyr.getPlayerName() + "\" ");
            int counter = 0;
        while (rSet.next()){
                try {
                    plyr.getPlayerCareer().getSeasons().get(counter).setRAPTOR(rSet.getDouble("raptor_total"));
                    counter++;
                    if (counter > (plyr.getPlayerCareer().getSeasons().size() - 1)) {
                        break; // RAPTOR data is recent, so with current players there are more seasons for each player in the RAPTOR table than in my season table
                    }
                } catch (IndexOutOfBoundsException ex){
                    System.out.println(plyr.getPlayerName());
                }
        }
    }

    public void addProspectRAPTOR(Player plyr) throws SQLException {
        statement = connect.createStatement();
        rSet = statement.executeQuery("SELECT raptor_total FROM historical_raptor_by_player WHERE player_name = \"" + plyr.getPlayerName() + "\" ");
        int counter = 0;
        if (!rSet.isBeforeFirst() ) {
            System.out.println("No data");
        }
        while (rSet.next()){
            try {
                plyr.getPlayerCareer().getSeasons().get(counter).setRAPTOR(rSet.getDouble("raptor_total"));
                counter++;
                if (counter > (plyr.getPlayerCareer().getSeasons().size() - 1)) {
                    break; // RAPTOR data is recent, so with current players there are more seasons for each player in the RAPTOR table than in my season table
                }
            } catch (IndexOutOfBoundsException ex){
                System.out.println(plyr.getPlayerName());
            }
        }
    }

}
