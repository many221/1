package com.company.players;

import com.company.cards.Card;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards = new ArrayList<> ();

    private final Player player;

    public Hand (String playerName){
        Player playee = new Player (playerName);
        player = playee;
    }

    public void addCard(Card card){
        cards.add ( card );
    }

    public void showCards(){
//        String output = cards.toString ();
//        System.out.println (output);
        //For testing
        String cardsInHand = "";
        for (int i = 0; i < cards.size () ; i++) {
           cardsInHand += "{" + (i + 1) + ". " + cards.get ( i )+"} ";
        }
        System.out.println (cardsInHand);
    }

   public Card playCard(int index){
        Card card = cards.get (index);
        cards.remove ( index );
        return card;
   }

   public String getPlayerName(){
        return player.getName ();
   }

   public int getHandSize(){
       // System.out.println (cards.size ());
       return cards.size ();
   }
}
