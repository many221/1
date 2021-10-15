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
    private Card playedCard;

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
        deck.shuffle ();
        dealCards ();
        while (true){
            turns ();
            emptyHandCheck ();
            System.out.println ("-".repeat ( 20 ));
            System.out.println ("-".repeat ( 20 ));
            deck.displayDeck ( 1 );
            deck.displayDeck ( 2 );
            System.out.println ("-".repeat ( 20 ));
            System.out.println ("-".repeat ( 20 ));
        }


    }
    //O}---Set initial card to only normal
    private void dealCards(){
        Console_output.dealingMsg ( intialCardCount );
        for (Hand hand : hands) {
            deck.deal ( hand, intialCardCount );
        }
        firstCard ();
       // System.out.println (deck.showPlayedCard ());
    }

    private void turns(){
        for (Hand hand : hands) {
            System.out.println ("-".repeat ( 20 ));
            turnActions ( hand );
            System.out.println ("-".repeat ( 20 ));
        }

    }


    private void turnActions(Hand activeHand){
        playDeck ();
        System.out.println (activeHand.getPlayerName () + "'s Turn");
        System.out.print ("Cards in Hand: ");
        activeHand.showCards ();
        int action = Console_output.getAction ();
        switch (action){
            case 1 -> {

                while(true) {

                    int card = Console_output.getPlayCard ( activeHand );

                    if(card == 0){
                        turnActions ( activeHand );
                        break;
                    }

                    int indexOfCard = card-1;

                    Card choosenCard = activeHand.playCard ( indexOfCard);

                    if (isPlayableCheck ( choosenCard )) {
                        playCard ( choosenCard,activeHand );
                        break;
                    }

                    System.out.print ("Played Card: " +  playedCard );
                    System.out.println ();
                    System.out.print ("Please Choose A Valid Card Or Enter 0 to go back");
                    activeHand.showCards ();



                }
            }
            //O}---Play after draw
            case 2 -> draw ( activeHand );
        }

    }

    private void playDeck(){
        System.out.println ("Played Card: {"+ playedCard + "}");
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

    private boolean isPlayableCheck(Card attemptCard){

        boolean colorMatch = playedCard.CARD_COLOR.matches ( attemptCard.CARD_COLOR );
        boolean valueMatch = playedCard.CARD_VALUE == attemptCard.CARD_VALUE;
        boolean blackCardHandCheck = attemptCard.CARD_VALUE > 12;
        //O}---Temp Till Hand Sets Color
        boolean blackCardDeckCheck = playedCard.CARD_VALUE >12;
        return colorMatch || valueMatch || blackCardHandCheck||blackCardDeckCheck;
    }


    //Check Deck Size

    private void firstCard(){
        Card card;

        while(true){
        card = deck.draw ();
        if (card.CARD_VALUE < 10){
            playedCard = card;
            deck.removeFromDrawDeck ( card );
            break;
        }
        }
    }

    private void playCard(Card card,Hand hand){
        deck.addToDiscardDeck ( playedCard );
        playedCard = card;
        hand.removeCard ( card );

    }

    private void draw(Hand hand){
      Card card = deck.draw ();
      hand.addCard ( card );
      deck.removeFromDrawDeck ( card );

    }

    private void actionCards(Card card, Hand hand){
       int cardValue = card.CARD_VALUE;
        switch (cardValue){
            case 10 ->{
                //Skip
               draw ( hand,0 );
            }
            case 11 ->{
                //Reverse

            }
            case 12 ->{
                //+2
                draw ( hand,1 );
            }
            case 13 ->{
                //wild
            }
            case 14 ->{
                //+4
                draw ( hand,2 );
            }
        }
    }
    private void draw(Hand hand , int num){


        switch (num){
            case 0 -> System.out.println ("You've Been Skipped");
            case 1 -> {
                for (int i = 0; i < 2; i++) {
                    Card card = deck.draw ();
                    hand.addCard ( card );
                    deck.removeFromDrawDeck ( card );
                }
            }
            case 2 -> {
                for (int i = 0; i < 4; i++) {
                    Card card = deck.draw ();
                    hand.addCard ( card );
                    deck.removeFromDrawDeck ( card );
                }
            }
        }

    }

    public void deckTesting(){
        for (int i = 0; i < 109; i++) {
            Card card = deck.draw ();
            deck.addToDiscardDeck ( card );
            deck.removeFromDrawDeck ( card );
            String dash = "---";
            System.out.println (dash.repeat ( 10 ));
            deck.displayDeck ( 1 );
            deck.displayDeck ( 2 );
            System.out.println (dash.repeat ( 10 ));
        }
    }

    }

