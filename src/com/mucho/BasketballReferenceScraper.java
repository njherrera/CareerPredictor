package com.mucho;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class BasketballReferenceScraper {

    // this method is going to, given an input player name, connect to the appropriate basketball reference page and grab that player's seasons, then use them to make a player object
    public static void connectToURL() throws IOException {
        Document doc = Jsoup.connect("https://www.basketball-reference.com/players/j/jordami01.html").get();
        String title = doc.title();
        System.out.println(title);
        Element content = doc.getElementById("switcher_advanced-playoffs_advanced");
        Element tables = content.getElementById("div_advanced");
        StringBuilder sb = new StringBuilder();
        for (Element elmt : tables.getAllElements()){
            if (elmt.id().contains("advanced")){
                sb.append(elmt.text() + "\n");
            }
        }
        int counter = 0;
        Scanner sc = new Scanner(sb.toString());
        while (sc.hasNextLine()){
            counter++;
            if (counter > 2){
                System.out.println(sc.nextLine());
                System.out.println(counter);
            } if (counter <= 2){
                sc.nextLine();
            }
        }
        Elements seasonTables = content.select(".fulltable");
    }
}
