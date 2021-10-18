package com.company.table;


import com.company.cards.Card;
import com.company.console.Console_output;
import com.company.deck.Standard_Deck;
import com.company.players.Hand;

import java.util.ArrayList;

public class Game {

    //Having a 3rd hidden option from players that is based off of switches and a boolean and
    //Potentially have another int to use for the switch based off of the boolean check

    //TODO-__- Fix multiple Card Drawing
    //TODO-__- Reverse Action Card
    //TODO-__-Figure where yo clear cardColor
    //TODO-__- UI Color Change Output
    //TODO-__- Fix Beginning infinite loop
    //TODO-__- Clean Up and Refactor code based off of SOLID & OOP
    //TODO-__- Minecraft hunger mechanic with death game implementation
    //TODO-__- Implement Stackabilty of cards
    //TODO-__- Graphics interface
    //TODO-__- Server based with multiple hands that can't be seen

    //O}_-_- FINISH BY SUNDAY!!!!!!

    private ArrayList<Hand> hands = new ArrayList<> ();
    private Standard_Deck deck = new Standard_Deck ();
    private int intialCardCount = 7;
    private int specialAction;
    private String cardColor = "";
    private Hand winner;
    private Card playedCard;

    public Game(){

        //TODO: Fix infinte yes no loop;
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

        switch (specialAction) {
            case 1 ->{
                System.out.println (activeHand.getPlayerName () +", You've Been Skipped!");
                actionReset ();
            }
//            case 2 ->{Reverse}
            case 3 ->{
                draw ( activeHand );
                draw ( activeHand );
            }
//            case 4 ->{Wild}
//            case 5 ->{+4}
            default -> {
            System.out.println ( activeHand.getPlayerName () + "'s Turn" );
            System.out.print ( "Cards in Hand: " + activeHand.getHandSize () + " :" );
            activeHand.showCards ();
            int action = Console_output.getAction ();
            switch (action) {
                case 1 -> {
                    //Figure out how to consolidate this into multiple  functions
                    //Add in an action card check and trigger
                    //Based off of whether they're affecting or being affected
                    //Add in wild card based off of the black color.

                    boolean check = false;
                    do {

                        int card = Console_output.getPlayCard ( activeHand );

                        if (card == 0) {
                            turnActions ( activeHand );
                            check = true;
                        }

                        int indexOfCard = card - 1;

                        Card choosenCard = activeHand.playCard ( indexOfCard );

                        check = cardCheck ( choosenCard, activeHand );
                        if(check){
                            if(choosenCard.CARD_COLOR == "⬛️"){
                               cardColor = colorCheck ( Console_output.getColor () );
                                System.out.println ("The Color is " + cardColor + ". ");
                            }
                            break;
                        }
                        System.out.print ( "Played Card: " + playedCard );
                        System.out.println ();
                        System.out.print ( "Please Choose A Valid Card Or Enter 0 to go back" );
                        activeHand.showCards ();


                    } while (!check);
                }
                //O}---Play after draw
                case 2 -> draw ( activeHand );
            }
        }
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
        boolean blackCardDeckCheck = playedCard.CARD_VALUE >12;
        boolean changedColorCheck  = cardColor.matches ( attemptCard.CARD_COLOR );

        return colorMatch || valueMatch || blackCardHandCheck|| (blackCardDeckCheck && changedColorCheck);

    }

    private boolean cardCheck(Card attempedCard, Hand activeHand ){

     boolean isPlayable = isPlayableCheck ( attempedCard );

     if (isPlayable){

         int value = attempedCard.CARD_VALUE;

         switch (value){

             case 10 ->{
                 specialAction = 1;
                 playCard ( attempedCard, activeHand );
             }
             case 11 ->{
                 specialAction = 2;
                 playCard ( attempedCard, activeHand );
             }
             case 12 ->{
                 specialAction = 3;
                 playCard ( attempedCard, activeHand );
             }
             case 13 ->{
                 specialAction = 4;
                 playCard ( attempedCard, activeHand );
             }
             case 14 ->{
                 specialAction = 5;
                 playCard ( attempedCard, activeHand );
             }
             default -> {
                 specialAction = 0;
                 playCard ( attempedCard, activeHand );
             }
         }
     }
        System.out.println ("Special Action: " + specialAction);
      return isPlayable;

    }

    private String colorCheck(int num){
        String color  = "";

        switch (num){
            case 1 ->{color ="\uD83D\uDFE5";}
            case 2 ->{color ="\uD83D\uDFE6";}
            case 3 ->{color ="\uD83D\uDFE9";}
            case 4 ->{color ="\uD83D\uDFE8";}
        }



        return color;
    };

    private void actionReset(){
        specialAction = 0;
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

    //TODO Figure out looping issue
    private void draw(Hand hand){
      Card card = deck.draw ();
      hand.addCard ( card );
      deck.removeFromDrawDeck ( card );

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

