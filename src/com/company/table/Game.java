package com.company.table;

import com.company.console.Console_output;
import com.company.deck.Standard_Deck;
import com.company.players.Hand;

import java.util.ArrayList;

public class Game {

    private ArrayList<Hand> hands = new ArrayList<> ();
    private  Standard_Deck deck = new Standard_Deck ();
    private int intialCardCount = 7;

    public Game(){

        Console_output.welcome_msg ();
            int playing = Console_output.playing ();

            for (int i = 0; i < playing; i++) {
                Hand hand = new Hand ( Console_output.playerNames () );
                hands.add ( hand );
            }




    }

    public void start(){
        dealCards ();
        turns ();

    }

    private void dealCards(){
        Console_output.dealingMsg ( intialCardCount );
        for (Hand hand : hands) {
            deck.deal ( hand, intialCardCount );
        }
    }

    private void turns(){
        for (Hand hand : hands) {
            turnActions ( hand );
        }

    }

    private void turnActions(Hand activeHand){
        activeHand.showCards ();
        int num = Console_output.getAction ( activeHand );
        switch (num){
            case 1 -> {
                int num2 = Console_output.getPlayCard ( activeHand );
               deck.addToSecondDeck ( activeHand.playCard ( num2 ) );
            }
            case 2 -> activeHand.addCard ( deck.draw () );
        }

    }

}
