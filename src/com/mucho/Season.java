package com.mucho;

public class Season {

    // I didn't think I would need this because of using an SQL database, but I still need to write code that compares 2 seasons (prospect vs historical player)
    // in order to compare seasons, this will also need variables to hold all of the stats
    // a season class is... inevitable because we need a way to store the stats of the player we're attempting to project

    private int year;
    private int age;
    private double trueShooting;
    private double freeThrowPercentage;
    private double usage;
    private double threePointRate;
    private double freeThrowRate;
    private double assistPercentage;
    private double turnoverPercentage;
    private double reboundPercentage;
    private double blockPercentage;
    private double stealPercentage;
    private double defensivePlusMinus;
}
