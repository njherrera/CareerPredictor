package com.mucho;

import java.util.ArrayList;
import java.util.Arrays;

public class Career {
    /*
    holds the seasons of a player's career
    tracks growth/regression across seasons
     */


    private ArrayList<Season> seasons;
    private double[] trueShootingGrowth;
    private double[] freeThrowPercentageGrowth;
    private double[] usageGrowth;
    private double[] threePointRateGrowth;
    private double[] freeThrowRateGrowth;
    private double[] assistPercentageGrowth;
    private double[] turnoverPercentageGrowth;
    private double[] reboundPercentageGrowth;
    private double[] blockPercentageGrowth;
    private double[] stealPercentageGrowth;
    private double[] defensivePlusMinusGrowth;

    public Career() {
        ArrayList<Season> career = new ArrayList<>();
        this.seasons = career;
    }

    public void addSeason(Season season){
        this.seasons.add(season);
    }

    public double compareToAnotherCareer(Career compareTo){
        double overallSimilarity = 0;

        overallSimilarity += compareCategory(this.trueShootingGrowth, compareTo.getTrueShootingGrowth());
        overallSimilarity += compareCategory(this.freeThrowPercentageGrowth, compareTo.getFreeThrowPercentageGrowth());
        overallSimilarity += compareCategory(this.usageGrowth, compareTo.getUsageGrowth());
        overallSimilarity += compareCategory(this.threePointRateGrowth, compareTo.getThreePointRateGrowth());
        overallSimilarity += compareCategory(this.freeThrowRateGrowth, compareTo.getFreeThrowRateGrowth());
        overallSimilarity += compareCategory(this.assistPercentageGrowth, compareTo.getAssistPercentageGrowth());
        overallSimilarity += compareCategory(this.turnoverPercentageGrowth, compareTo.getTurnoverPercentageGrowth());
        overallSimilarity += compareCategory(this.reboundPercentageGrowth, compareTo.getReboundPercentageGrowth());
        overallSimilarity += compareCategory(this.blockPercentageGrowth, compareTo.getBlockPercentageGrowth());
        overallSimilarity += compareCategory(this.stealPercentageGrowth, compareTo.getStealPercentageGrowth());
        overallSimilarity += compareCategory(this.defensivePlusMinusGrowth, compareTo.getDefensivePlusMinusGrowth());

        return overallSimilarity / 11;
    }

    // like with comparePerforamnce in ComparisonTool, we find the shortest career length of the two careers and use it for the for loop (to avoid indexing errors)
    public double compareCategory(double[] thisCareerCategory, double[] otherCareerCategory){
        double shortestCareerLength = Math.min(thisCareerCategory.length, otherCareerCategory.length);
        double overallSimilarity = 0;
        for(int i = 0; i < shortestCareerLength; i++){
            double biggestNumber = Math.max(thisCareerCategory[i], otherCareerCategory[i]);
            double smallestNumber = Math.min(thisCareerCategory[i], otherCareerCategory[i]);
            double similarity = Math.abs((biggestNumber - smallestNumber) / biggestNumber);
            overallSimilarity += similarity;
        }
        return overallSimilarity / 11;
    }

    public void chartGrowth(){
        chartTrueShootingGrowth();
        chartFTPercentageGrowth();
        chartUsageGrowth();
        chartThreePointRateGrowth();
        chartFreeThrowRateGrowth();
        chartAssistPercentageGrowth();
        chartTurnoverPercentageGrowth();
        chartReboundPercentageGrowth();
        chartBlockPercentageGrowth();
        chartStealPercentageGrowth();
        chartDefensivePlusMinusGrowth();
    }


    // this and other chart____Growth methods loop through each season and compare production to the previous season, starting at the 2nd season
    public void chartTrueShootingGrowth(){
        trueShootingGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getTrueShooting() - this.seasons.get(i -1).getTrueShooting();
            trueShootingGrowth[i - 1] = change;
        }
    }

    public void chartFTPercentageGrowth(){
        freeThrowPercentageGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getFreeThrowPercentage() - this.seasons.get(i -1).getFreeThrowPercentage();
            freeThrowPercentageGrowth[i - 1] = change;
        }
    }

    public void chartUsageGrowth(){
        usageGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getUsage() - this.seasons.get(i -1).getUsage();
            usageGrowth[i - 1] = change;
        }
    }

    public void chartThreePointRateGrowth(){
        threePointRateGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getThreePointRate() - this.seasons.get(i -1).getThreePointRate();
            threePointRateGrowth[i - 1] = change;
        }
    }

    public void chartFreeThrowRateGrowth(){
        freeThrowRateGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getFreeThrowRate() - this.seasons.get(i -1).getFreeThrowRate();
            freeThrowRateGrowth[i - 1] = change;
        }
    }

    public void chartAssistPercentageGrowth(){
        assistPercentageGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getAssistPercentage() - this.seasons.get(i -1).getAssistPercentage();
            assistPercentageGrowth[i - 1] = change;
        }
    }

    public void chartTurnoverPercentageGrowth(){
        turnoverPercentageGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getTurnoverPercentage() - this.seasons.get(i -1).getTurnoverPercentage();
            turnoverPercentageGrowth[i - 1] = change;
        }
    }

    public void chartReboundPercentageGrowth(){
        reboundPercentageGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getReboundPercentage() - this.seasons.get(i -1).getReboundPercentage();
            reboundPercentageGrowth[i - 1] = change;
        }
    }

    public void chartBlockPercentageGrowth(){
        blockPercentageGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getBlockPercentage() - this.seasons.get(i -1).getBlockPercentage();
            blockPercentageGrowth[i - 1] = change;
        }
    }

    public void chartStealPercentageGrowth(){
        stealPercentageGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getStealPercentage() - this.seasons.get(i -1).getStealPercentage();
            stealPercentageGrowth[i - 1] = change;
        }
    }
    public void chartDefensivePlusMinusGrowth(){
        defensivePlusMinusGrowth = new double[this.seasons.size() - 1];
        for(int i = 1; i < this.seasons.size(); i++){
            double change = this.seasons.get(i).getDefensivePlusMinus() - this.seasons.get(i -1).getDefensivePlusMinus();
            defensivePlusMinusGrowth[i - 1] = change;
        }
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public double[] getTrueShootingGrowth() {
        return trueShootingGrowth;
    }

    public void setTrueShootingGrowth(double[] trueShootingGrowth) {
        this.trueShootingGrowth = trueShootingGrowth;
    }

    public double[] getFreeThrowPercentageGrowth() {
        return freeThrowPercentageGrowth;
    }

    public void setFreeThrowPercentageGrowth(double[] freeThrowPercentageGrowth) {
        this.freeThrowPercentageGrowth = freeThrowPercentageGrowth;
    }

    public double[] getUsageGrowth() {
        return usageGrowth;
    }

    public void setUsageGrowth(double[] usageGrowth) {
        this.usageGrowth = usageGrowth;
    }

    public double[] getThreePointRateGrowth() {
        return threePointRateGrowth;
    }

    public void setThreePointRateGrowth(double[] threePointRateGrowth) {
        this.threePointRateGrowth = threePointRateGrowth;
    }

    public double[] getFreeThrowRateGrowth() {
        return freeThrowRateGrowth;
    }

    public void setFreeThrowRateGrowth(double[] freeThrowRateGrowth) {
        this.freeThrowRateGrowth = freeThrowRateGrowth;
    }

    public double[] getAssistPercentageGrowth() {
        return assistPercentageGrowth;
    }

    public void setAssistPercentageGrowth(double[] assistPercentageGrowth) {
        this.assistPercentageGrowth = assistPercentageGrowth;
    }

    public double[] getTurnoverPercentageGrowth() {
        return turnoverPercentageGrowth;
    }

    public void setTurnoverPercentageGrowth(double[] turnoverPercentageGrowth) {
        this.turnoverPercentageGrowth = turnoverPercentageGrowth;
    }

    public double[] getReboundPercentageGrowth() {
        return reboundPercentageGrowth;
    }

    public void setReboundPercentageGrowth(double[] reboundPercentageGrowth) {
        this.reboundPercentageGrowth = reboundPercentageGrowth;
    }

    public double[] getBlockPercentageGrowth() {
        return blockPercentageGrowth;
    }

    public void setBlockPercentageGrowth(double[] blockPercentageGrowth) {
        this.blockPercentageGrowth = blockPercentageGrowth;
    }

    public double[] getStealPercentageGrowth() {
        return stealPercentageGrowth;
    }

    public void setStealPercentageGrowth(double[] stealPercentageGrowth) {
        this.stealPercentageGrowth = stealPercentageGrowth;
    }

    public double[] getDefensivePlusMinusGrowth() {
        return defensivePlusMinusGrowth;
    }

    public void setDefensivePlusMinusGrowth(double[] defensivePlusMinusGrowth) {
        this.defensivePlusMinusGrowth = defensivePlusMinusGrowth;
    }

    @Override
    public String toString() {
        return "Career{" +
                "trueShootingGrowth=" + Arrays.toString(trueShootingGrowth) +
                ", freeThrowPercentageGrowth=" + Arrays.toString(freeThrowPercentageGrowth) +
                ", usageGrowth=" + Arrays.toString(usageGrowth) +
                ", threePointRateGrowth=" + Arrays.toString(threePointRateGrowth) +
                ", freeThrowRateGrowth=" + Arrays.toString(freeThrowRateGrowth) +
                ", assistPercentageGrowth=" + Arrays.toString(assistPercentageGrowth) +
                ", turnoverPercentageGrowth=" + Arrays.toString(turnoverPercentageGrowth) +
                ", reboundPercentageGrowth=" + Arrays.toString(reboundPercentageGrowth) +
                ", blockPercentageGrowth=" + Arrays.toString(blockPercentageGrowth) +
                ", stealPercentageGrowth=" + Arrays.toString(stealPercentageGrowth) +
                ", defensivePlusMinusGrowth=" + Arrays.toString(defensivePlusMinusGrowth) +
                '}';
    }
}
