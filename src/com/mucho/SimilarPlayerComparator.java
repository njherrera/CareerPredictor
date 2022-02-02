package com.mucho;

import java.util.Comparator;

public class SimilarPlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2){
        return Double.compare(p1.getSimilarityScore(), p2.getSimilarityScore());
    }
}
