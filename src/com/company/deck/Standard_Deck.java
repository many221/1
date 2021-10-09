package com.company.deck;

import com.company.cards.Card;
import com.company.utilites.random_num;

import java.util.ArrayList;
import java.util.Objects;

public class Standard_Deck implements Deck{


    private final String[] COLORS = {"🟥","🟦","🟩","🟨","⬛️"};

    private final int[] VALUES = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};

    private ArrayList<Card> first_deck = new ArrayList<> ();

    private ArrayList<Card> second_deck = new ArrayList<> ();


    public Standard_Deck(){

        for (String color:COLORS) {

            boolean isBlack = color.equals ( "⬛️" );

            for (int num:VALUES) {

                switch (num){
                    case 0 ->{
                        if (!isBlack){
                        Card card = new Card ( num, color );
                        first_deck.add ( card );
                        }
                    }
                    case 13,14 ->{
                        if (isBlack)
                        for (int i = 0; i < 4; i++) {
                            Card card = new Card ( num, color );
                            first_deck.add ( card );
                        }
                    }
                    default -> {
                        if (!isBlack)
                        for (int i = 0; i < 2; i++) {
                            Card card = new Card ( num, color );
                            first_deck.add ( card );
                        }
                    }

                }

            }
        }
    }


    public void displayDeck(){

        String output = first_deck.toString ();

        System.out.println (output + "\n" + first_deck.size ());

    }


    public Card DrawRandom(){

        int index = random_num.num (0, first_deck.size ()-1);

        Card card = first_deck.get ( index );

        //Testing Deck card swapping
//        first_deck.remove ( index );
//        second_deck.add (card);
//        System.out.println (first_deck.size () + " 1|2 " + second_deck.size ());
        return card;

    }


    public Card draw(int num) {
        return first_deck.get ( num );
    }


    @Override
    public Card draw() {
        return null;
    }
}
