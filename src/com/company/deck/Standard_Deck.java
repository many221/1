package com.company.deck;

import com.company.cards.Card;
import com.company.players.Hand;
import com.company.utilites.Random_num;

import java.util.ArrayList;
import java.util.Collections;

public class Standard_Deck implements Deck{


    private final ArrayList<Card> drawDeck = new ArrayList<> ();

    private final ArrayList<Card> discardDeck = new ArrayList<> ();


    public Standard_Deck(){

        String[] COLORS = {"🟥", "🟦", "🟩", "🟨", "⬛️"};
        for (String color: COLORS) {

            boolean isBlack = color.equals ( "⬛️" );

            int[] VALUES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
            for (int num: VALUES) {

                switch (num){
                    case 0 ->{
                        if (!isBlack){
                        Card card = new Card ( num, color );
                        drawDeck.add ( card );
                        }
                    }
                    case 13,14 ->{
                        if (isBlack)
                        for (int i = 0; i < 4; i++) {
                            Card card = new Card ( num, color );
                            drawDeck.add ( card );
                        }
                    }
                    default -> {
                        if (!isBlack)
                        for (int i = 0; i < 2; i++) {
                            Card card = new Card ( num, color );
                            drawDeck.add ( card );
                        }
                    }

                }

            }
        }

    }


    public void displayDeck(int deck){

        String deckName = "";

        int size = deckSize ( deck );

        switch (deck){
            case 1 -> deckName = "Draw Deck";
            case 2 -> deckName = "Discard Deck";
        }

        System.out.println ( "Deck " + deckName + ": "+ size);

    }

    private int deckSize(int deck){
        int size = 0;

        switch (deck){
            case 1 -> size = drawDeck.size ();
            case 2 -> size = discardDeck.size ();

        }
        return size;
    }

    public void addToDiscardDeck(Card card){
        discardDeck.add ( card );
    }

    public void removeFromDrawDeck(Card card){
        drawDeck.remove ( card );
    }

    public void shuffle(){
        Collections.shuffle ( drawDeck );
    }

    @Override
    public Card draw() {
        deckCheck ();
        Card card = drawDeck.get ( 0 );
        return card;
    }

    @Override
    public void deal(Hand hand, int num) {
        for (int i = 0; i < num; i++) {
            Card card = draw ();
            hand.addCard ( card );
            removeFromDrawDeck ( card );
        }
    }


    public void deckCheck(){
        if(deckSize ( 1 ) == 1){

         drawDeck.addAll ( discardDeck);
         discardDeck.clear ();
         shuffle ();

        }
    }


        }



