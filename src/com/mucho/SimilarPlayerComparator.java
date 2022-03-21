package com.mucho;

import java.util.Comparator;

public class SimilarPlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2){
        return Double.compare(p2.getSimilarityScore(), p1.getSimilarityScore());
    }
}
