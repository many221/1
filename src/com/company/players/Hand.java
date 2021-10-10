package com.company.players;

import com.company.cards.Card;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards = new ArrayList<> ();

    private final Actors player;

    public Hand (Player player){
        this.player = player;
    }

    public void addCard(Card card){
        cards.add ( card );
    }

    public void showCards(){
//        String output = cards.toString ();
//        System.out.println (output);
        //For testing
        for (int i = 0; i < cards.size () ; i++) {
            System.out.println ("Index " + i + ": " + cards.get ( i ));
        }
    }

   public Card playCard(int index){
        Card card = cards.get (index);
        cards.remove ( index );
        return card;
   }

    public void getHandSize(){
        System.out.println (cards.size ());
    }
}
