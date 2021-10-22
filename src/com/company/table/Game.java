package com.company.table;
import com.company.cards.Card;
import com.company.console.Console_output;
import com.company.deck.Standard_Deck;
import com.company.players.Hand;
import java.util.ArrayList;

public class Game {

    //New Branch here
    //TODO-__- Actor Classes
    //New Branch here
    //TODO-__- Clean Up and Refactor code based off of SOLID & OOP
    //New Branch Here
    //TODO-__- Implement Stackabilty of cards
    //TODO-__- Rage Quit
    //New Branch here
    //TODO-__- Minecraft hunger mechanic with death game implementation
    //TODO-__- Graphics interface
    //TODO-__- Server based with multiple hands that can't be seen

    private ArrayList<Hand> hands = new ArrayList<> ();
    private Standard_Deck deck = new Standard_Deck ();
    private int intialCardCount = 7;
    private int specialAction;
    private int direction;
    private String cardColor = "";
    private Hand winner;
    private Card playedCard;

    public Game(){

        //TODO: Fix infinte yes no loop;
        boolean start =  Console_output.welcome_msg ();
       if ( start) {
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
        System.out.println ("-".repeat ( 20 ));
        System.out.println ("Shuffled");
        System.out.println ("-".repeat ( 20 ));
        firstCard ();
        System.out.println ("First Card Dealt");
        System.out.println ("-".repeat ( 20 ));
        dealCards ();
        System.out.println ("Player Cards Dealt");
        System.out.println ("-".repeat ( 20 ));
        while (true){
            turns ();
        }

    }

    private void dealCards(){

        Console_output.dealingMsg ( intialCardCount );
        for (Hand hand : hands) {
            deck.deal ( hand, intialCardCount );
        }

       // System.out.println (deck.showPlayedCard ());
    }

    private void turns(){

        while(true){

            for (int i = 0; i < hands.size (); ) {

                switch (direction) {
                    case 0 -> {
                        if(specialAction == 2){
                            i++;
                        }
                        Hand hand = hands.get ( i );
                        System.out.println ("-".repeat ( 20 ));
                        turnActions ( hand );
                        emptyHandCheck ( hand);
                        System.out.println ("-".repeat ( 20 ));
                        i++;

                    }

                    case 1 -> {
                        if(i == 0){
                            i = hands.size ();
                        }
                        if(specialAction == 2){
                            i--;
                        }
                        i--;
                        Hand hand = hands.get ( i );
                        System.out.println ("-".repeat ( 20 ));
                        turnActions ( hand );
                        System.out.println ("-".repeat ( 20 ));
                        emptyHandCheck ( hand);
                        System.out.println ("-".repeat ( 20 ));

                    }
                }
            }

        }
    }

    private void turnActions(Hand activeHand){
        playDeck ();
        System.out.println ("-".repeat ( 20 ));
        switch (specialAction) {
            //Skipped
            case 1 ->{
                System.out.println (activeHand.getPlayerName () +", You've Been Skipped!");
                actionReset ();
            }
            //{Draw 2}
            case 3 ->{
                System.out.println (activeHand.getPlayerName () +", You've Been Skipped And Have Drawn 2!");
                draw ( activeHand,2 );
                actionReset ();
            }
            //{Draw 4}
            case 5 ->{
                System.out.println (activeHand.getPlayerName () +", You've Been Skipped And Have Drawn 4!");
                draw ( activeHand,4 );
                actionReset ();
            }
            default -> {
                boolean resart = true;
            do {
                System.out.println ( activeHand.getPlayerName () + "'s Turn" );
                System.out.print ( "Cards in Hand: " + activeHand.getHandSize () + " :" );
                activeHand.showCards ();
                int action = Console_output.getAction ();
                switch (action) {
                    case 1 -> {

                        do {
                            int card = Console_output.getPlayCard ( activeHand );
                            //X> Recursion Issues with index out of bounds
                            if (card == 0) {
                              draw ( activeHand );
                              break;
                            }

                            Card choosenCard = activeHand.playCard (card - 1);

                            boolean check = cardCheck ( choosenCard, activeHand );
                            if (check) {
                                //Color changing action
                                if (choosenCard.CARD_COLOR.equals ( "⬛️" )) {
                                    cardColor = colorCheck ( Console_output.getColor () );
                                }
                                break;
                            } else {System.out.print ( "Please Choose A Valid Card Or Enter 0 to go back" );}

                        } while (true);
                        if (playedCard.CARD_COLOR.equals ( "⬛️" )) {
                            System.out.print ( "The Color is " + cardColor + ". " );
                        }
                        System.out.println ( "Played Card: " + playedCard );
                        System.out.println ();

                        activeHand.showCards ();
                        resart = false;
                    }
                    case 2 -> {
                        draw ( activeHand );
                        resart = false;
                    }
                }
            }while (resart);
         }
        }
    }
    private void turnActions(Hand activeHand, int act){
        playDeck ();
        switch (act) {
            //Skipped
            case 1 ->{
//                System.out.println (activeHand.getPlayerName () +", You've Been Skipped!");
//                actionReset ();
            }
            //{Draw 2}
            case 3 ->{
//                System.out.println (activeHand.getPlayerName () +", You've Been Skipped And Have Drawn 2!");
                draw ( activeHand,2 );
//                actionReset ();
            }
            //{Draw 4}
            case 5 ->{
//                System.out.println (activeHand.getPlayerName () +", You've Been Skipped And Have Drawn 4!");
                draw ( activeHand,4 );
//                actionReset ();
            }
            default -> {

                System.out.println ( activeHand.getPlayerName () + "'s Turn" );
                System.out.print ( "Cards in Hand: " + activeHand.getHandSize () + " :" );
                activeHand.showCards ();
                int action = Console_output.getAction ();

                switch (action) {
                    case 1 -> {

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
                                //Color changing action
                                if(choosenCard.CARD_COLOR.equals ( "⬛️" )){
                                    cardColor = colorCheck ( Console_output.getColor () );
                                }
                                break;
                            }
                            if(playedCard.CARD_COLOR.equals ( "⬛️" )) {
                                System.out.print ("The Color is " + cardColor + ". ");
                            }
                            System.out.println ( "Played Card: " + playedCard );
                            System.out.println ();
                            System.out.print ( "Please Choose A Valid Card Or Enter 0 to go back" );
                            activeHand.showCards ();

                        } while (!check);
                    }

                    case 2 -> {draw ( activeHand );}
                }
            }
        }
    }


    private void playDeck(){
        if(playedCard.CARD_COLOR.equals ( "⬛️" )) {
            System.out.print ("The Color is " + cardColor + ". ");
        }
        System.out.println ("Played Card: {"+ playedCard + "}");
    }

    private void emptyHandCheck(Hand hand){
        if(hand.isEmpty ()){
            winner = hand;
            System.out.println (winner.getPlayerName () + " Has Won");
            System.exit ( 0 );
        }
        if(hand.getHandSize () == 1){
            System.out.println (hand.getPlayerName () + " Has Uno! All Players Must Now Target Them Before They Win!!");
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

    //SpecialActions Are In Here
    private boolean cardCheck(Card attempedCard, Hand activeHand ){

     boolean isPlayable = isPlayableCheck ( attempedCard );

     if (isPlayable){

         int value = attempedCard.CARD_VALUE;

         switch (value){

             case 10 ->{
                 //Skip
                 specialAction = 1;
                 playCard ( attempedCard, activeHand );
             }
             case 11 ->{
                 //Reverse
                 specialAction = 2;
                 if(direction == 0){
                     direction = 1;
                 }else {direction = 0;}
                 playCard ( attempedCard, activeHand );
             }
             case 12 ->{
                 //+2
                 specialAction = 3;
                 playCard ( attempedCard, activeHand );
             }
             case 13 ->{
                 //Wild
                 specialAction = 4;
                 playCard ( attempedCard, activeHand );
             }
             case 14 ->{
                 //+4
                 specialAction = 5;
                 playCard ( attempedCard, activeHand );
             }
             default -> {
                 specialAction = 0;
                 playCard ( attempedCard, activeHand );
             }
         }
     }
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


            card = deck.draw ();
            playedCard = card;
            deck.removeFromDrawDeck ( card );
            actionReset ();


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

    private void draw(Hand hand, int num){
       ArrayList<Card> drawn = drawMutliple ( num );
        for (Card card:
             drawn) {
            hand.addCard ( card );
        }
    }

    private ArrayList<Card> drawMutliple(int num){
        ArrayList<Card> Drawn =  new ArrayList<Card>();
        for (int i = 0; i < num; i++) {
            Card card = deck.draw ();
            Drawn.add ( card );
            deck.removeFromDrawDeck ( card );
        }
//        String output = Drawn.toString ();
//        System.out.println (output);
        return Drawn;
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

    public void drawTesting(){
        deck.shuffle ();
        drawMutliple ( 4 );
    }

    }

