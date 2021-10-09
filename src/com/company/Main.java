package com.company;

import com.company.deck.Standard_Deck;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Standard_Deck deck = new Standard_Deck ();
        //deck.displayDeck ();
        System.out.println ( deck.DrawRandom () );

    }
}
