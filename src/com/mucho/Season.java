package com.mucho;

public class Season {

    // I didn't think I would need this because of using an SQL database, but I still need to write code that compares 2 seasons (prospect vs historical player)
    // in order to compare seasons, this will also need variables to hold all of the stats
    // a season class is... inevitable because we need a way to store the stats of the player we're attempting to project

    private String playerName;
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
    private double RAPTOR;

    public Season(String playerName, int year, int age) {
        this.playerName = playerName;
        this.year = year;
        this.age = age;
    }

    public Season(String playerName) {
        this.playerName = playerName;
    }

    // wrote this method to make the compareToAnotherSeason method easier to read, write, and understand
    public double compareCategory(double thisSeasonCategory, double otherSeasonCategory){
        double largerCategory = Math.max(thisSeasonCategory, otherSeasonCategory);
        double smallerCategory = Math.min(thisSeasonCategory, otherSeasonCategory);
        double similarity = smallerCategory / largerCategory;
        return similarity;
    }

    public double compareToAnotherSeason(Season compareTo){
        double overallSimilarity = 0;

        overallSimilarity += this.compareCategory(this.trueShooting, compareTo.getTrueShooting());
        overallSimilarity += this.compareCategory(this.freeThrowPercentage, compareTo.getFreeThrowPercentage());
        overallSimilarity += this.compareCategory(this.usage, compareTo.getUsage());
        overallSimilarity += this.compareCategory(this.threePointRate, compareTo.getThreePointRate());
        overallSimilarity += this.compareCategory(this.freeThrowRate, compareTo.getFreeThrowRate());
        overallSimilarity += this.compareCategory(this.assistPercentage, compareTo.getAssistPercentage());
        overallSimilarity += this.compareCategory(this.turnoverPercentage, compareTo.getTurnoverPercentage());
        overallSimilarity += this.compareCategory(this.reboundPercentage, compareTo.getReboundPercentage());
        overallSimilarity += this.compareCategory(this.blockPercentage, compareTo.getBlockPercentage());
        overallSimilarity += this.compareCategory(this.stealPercentage, compareTo.getStealPercentage());

        double dividedSimilarity = overallSimilarity / 10;
        return dividedSimilarity;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getTrueShooting() {
        return trueShooting;
    }

    public void setTrueShooting(double trueShooting) {
        this.trueShooting = trueShooting;
    }

    public double getFreeThrowPercentage() {
        return freeThrowPercentage;
    }

    public void setFreeThrowPercentage(double freeThrowPercentage) {
        this.freeThrowPercentage = freeThrowPercentage;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    public double getThreePointRate() {
        return threePointRate;
    }

    public void setThreePointRate(double threePointRate) {
        this.threePointRate = threePointRate;
    }

    public double getFreeThrowRate() {
        return freeThrowRate;
    }

    public void setFreeThrowRate(double freeThrowRate) {
        this.freeThrowRate = freeThrowRate;
    }

    public double getAssistPercentage() {
        return assistPercentage;
    }

    public void setAssistPercentage(double assistPercentage) {
        this.assistPercentage = assistPercentage;
    }

    public double getTurnoverPercentage() {
        return turnoverPercentage;
    }

    public void setTurnoverPercentage(double turnoverPercentage) {
        this.turnoverPercentage = turnoverPercentage;
    }

    public double getReboundPercentage() {
        return reboundPercentage;
    }

    public void setReboundPercentage(double reboundPercentage) {
        this.reboundPercentage = reboundPercentage;
    }

    public double getBlockPercentage() {
        return blockPercentage;
    }

    public void setBlockPercentage(double blockPercentage) {
        this.blockPercentage = blockPercentage;
    }

    public double getStealPercentage() {
        return stealPercentage;
    }

    public void setStealPercentage(double stealPercentage) {
        this.stealPercentage = stealPercentage;
    }


    @Override
    public String toString() {
        return "Season{" +
                "playerName='" + playerName + '\'' +
                ", year=" + year +
                ", age=" + age +
                ", trueShooting=" + trueShooting +
                ", freeThrowPercentage=" + freeThrowPercentage +
                ", usage=" + usage +
                ", threePointRate=" + threePointRate +
                ", freeThrowRate=" + freeThrowRate +
                ", assistPercentage=" + assistPercentage +
                ", turnoverPercentage=" + turnoverPercentage +
                ", reboundPercentage=" + reboundPercentage +
                ", blockPercentage=" + blockPercentage +
                ", stealPercentage=" + stealPercentage +
                '}';
    }
}
