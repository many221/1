package com.company;

import com.company.cards.Card;
import com.company.deck.Standard_Deck;
import com.company.players.Hand;
import com.company.players.Player;
import com.company.table.Game;

public class Main {

    public static void main(String[] args) {
	// write your code here

//        Standard_Deck deck = new Standard_Deck ();
//        //deck.displayDeck ();
//
//        Player bob = new Player ( "Bob" );
//        Hand bobhand = new Hand ( bob );
//
//        for (int i = 0; i < 7; i++) {
//            Card card = deck.DrawRandom ();
//            bobhand.addCard ( card );
//        }
//        bobhand.showCards ();
//
//
//            deck.addToSecondDeck (  bobhand.playCard (4 )  );
//
//
//        deck.displayDeck ( 1 );
//        bobhand.getHandSize ();
//        deck.displayDeck ( 2 );
    Game game = new Game ();
    game.start ();
//        game.drawTesting ();

    }
}
