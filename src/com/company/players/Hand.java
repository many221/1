package com.company.players;

import com.company.cards.Card;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards = new ArrayList<> ();

    private Actors player;

    public Hand (Player player){
        this.player = player;
    }

    public void addCard(Card card){
        cards.add ( card );
    }

    public void showCards(){
        String output = cards.toString ();
        System.out.println (output);
    }

    public Card playCard(int index){
        Card card = cards.get (index);
        cards.remove ( index );
        return card;
    }

}
