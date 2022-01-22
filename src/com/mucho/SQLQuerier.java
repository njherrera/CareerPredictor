package com.mucho;

import java.sql.*;
import java.util.ArrayList;

public class SQLQuerier {

    /*
    interacts with the SQL database
    pulls players with similar rookie year age to prospect, then creates Player objects for each of them
    pulls seasons of each queried player, then creates Season objects for each of them
     */

    private Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/nbaplayers", "root", "antistap1zza");
    private Statement statement = null;
    private ResultSet rSet = null;
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
            System.out.println("ID: " + rSet.getInt("playerID"));
            System.out.println(", Name: " + rSet.getString("name"));
            System.out.println(", Height: " + rSet.getInt("height"));
            System.out.println(", Weight: " + rSet.getInt("weight"));
            System.out.println(", Rookie Age: " + rSet.getInt("rookieYearAge"));
            Player newPlayer = new Player(rSet.getString("name"), rSet.getInt("height"), rSet.getInt("weight"), rSet.getInt("rookieYearAge"));
            sameAgePlayers.add(newPlayer);
        }
        return sameAgePlayers;
    }

    // mostly a bug-fixing method, used so that
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
            newSeason.setDefensivePlusMinus(rSet.getDouble("defensivePlusMinus"));
            plyr.addSeasonToCareer(newSeason);
        }
    }

}
