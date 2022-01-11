package com.mucho;

import java.sql.*;

public class SQLQuerier {

    /*
    interacts with the SQL database
    pulls players with similar rookie year age to prospect, then creates Player objects for each of them
    pulls seasons of each queried player, then creates Season objects for each of them
     */

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet rSet = null;
    private PreparedStatement preparedStatement = null;

    public void selectPlayersSameAge(int age) throws SQLException {

        try{
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/nbaplayers", "root", "antistap1zza");
            statement = connect.createStatement();
            rSet = statement.executeQuery("SELECT * FROM player WHERE rookieYearAge = " + age);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } while (rSet.next()){
            System.out.println("ID: " + rSet.getInt("playerID"));
            System.out.println(", Name: " + rSet.getString("name"));
            System.out.println(", Height: " + rSet.getInt("height"));
            System.out.println(", Weight: " + rSet.getInt("weight"));
            System.out.println(", Rookie Age: " + rSet.getInt("rookieYearAge"));
        }
    }
}
