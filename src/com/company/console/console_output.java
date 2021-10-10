package com.company.console;

import com.company.players.Hand;

public abstract class console_output {

    public static boolean welcome_msg(){
        System.out.println ("Hello There! \nWelcome  To Manny's One Game!");
        String yn = console_input.getString ("Would You Like To Play?", true);
        return console_input.yesNO ( yn );
    }

    public static int playing(){
        return console_input.getInt ( "How Many People Are Playing Today?",2,8,"Please Enter A Number Between 2-8" );
    }

    public static String playerNames(){
        return console_input.getString ( "Please Enter Player Name: ", true );
    }

    public static void startingMsg(int cards){
        System.out.println ("Let The Game Begin!! \nAll Players Have Been dealt " + cards + " cards.");
    }

    public static int getAction(Hand activehand){
        System.out.println (activehand.getPlayerName () + "'s Turn");
        return  console_input.getInt ("Please Choose One: \n1. Play Card \n 2. Draw Card",1,2,"PLease Enter A Number Between 1-2"  );

    }

    public static int getPlayedCard(Hand activehand){
        int handSize = activehand.getHandSize ();
        return console_input.getInt ( "Please Enter Card Number. ", 1,handSize,"Please Enter A Number 1-" + handSize  );
    }
}
