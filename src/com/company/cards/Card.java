package com.company.cards;

public class Card {

    public final int CARD_VALUE;

    public final String CARD_COLOR;


    public Card(int CARD_VALUE, String CARD_COLOR) {

        this.CARD_VALUE = CARD_VALUE;

        this.CARD_COLOR = CARD_COLOR;

    }

    public String toString(){

        String output = "";

        switch (CARD_VALUE){
            case 10 ->{output = "Skip 🚫";}
            case 11 ->{output = "Reverse 🔃";}
            case 12 ->{output = "+2";}
            case 13 ->{output = "WILD 🔠";}
            case 14 ->{output = "+4";}
            default -> output = Integer.toString ( CARD_VALUE );
        }

      return "| " + output + " " + CARD_COLOR + " |";
    }

}
