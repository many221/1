package com.company.table;


import com.company.cards.Card;
import com.company.console.Console_output;
import com.company.deck.Standard_Deck;
import com.company.players.Hand;

import java.util.ArrayList;

public class Game {

    private ArrayList<Hand> hands = new ArrayList<> ();
    private Standard_Deck deck = new Standard_Deck ();
    private int intialCardCount = 7;
    private Hand winner;

    public Game(){

       if ( Console_output.welcome_msg ()) {
           int playing = Console_output.playing ();
           for (int i = 0; i < playing; i++) {
               Hand hand = new Hand ( Console_output.playerNames () );
               hands.add ( hand );
           }
       }else {
           System.out.println ("Bye Bye!");
           System.exit ( 0 );
       }

    }

    public void start(){
        dealCards ();
        while (true){
            turns ();
            emptyHandCheck ();
        }


    }
    //O}---Set initial card to only normal
    private void dealCards(){
        Console_output.dealingMsg ( intialCardCount );
        for (Hand hand : hands) {
            deck.deal ( hand, intialCardCount );
        }
        deck.intialPLayCard ();
        deck.removeFromDrawDeck ( deck.showPlayedCard () );
       // System.out.println (deck.showPlayedCard ());
    }

    private void turns(){
        for (Hand hand : hands) {
            turnActions ( hand );
        }

    }

    private void turnActions(Hand activeHand){
        playDeck ();
        System.out.println (activeHand.getPlayerName () + "'s Turn");
        System.out.print ("Cards in Hand: ");
        activeHand.showCards ();
        int num = Console_output.getAction ();
        switch (num){
            case 1 -> {
                int num2 = Console_output.getPlayCard ( activeHand ) - 1;
               deck.addToSecondDeck ( activeHand.playCard ( num2 ) );
            }
            case 2 -> activeHand.addCard ( deck.draw () );
        }

    }

    private void playDeck(){
        System.out.println ("Played Card: {"+ deck.showPlayedCard ()+ "}");
    }


    private void emptyHandCheck(){
        for (Hand hand: hands) {
            if(hand.isEmpty ()){
                winner = hand;
                System.out.println (winner.getPlayerName () + " Has Won");
                System.exit ( 0 );
            }
        }

    }

    private void isPlayableCheck(Card card){
        boolean colorCheck = card.CARD_COLOR.matches ( deck.showPlayedCard ().CARD_COLOR );
        boolean numCheck = card.CARD_VALUE == deck.showPlayedCard ().CARD_VALUE;
        if(numCheck || colorCheck){

        }
    }
}
