package com.company.deck;

import com.company.cards.Card;
import com.company.players.Hand;

public interface Deck {

    public Card draw();

    public void deal(Hand hand , int num);
}
