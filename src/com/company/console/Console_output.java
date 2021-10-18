package com.company.console;

import com.company.players.Hand;

public abstract class Console_output {

    public static boolean welcome_msg(){
        boolean check;
        System.out.println ("Hello There! \nWelcome  To Manny's One Game!");
        check = Console_input.yesNO (Console_input.getString ("Would You Like To Play?\nYy:Nn", true) );
        return check;
    }

    public static int playing(){
        return Console_input.getInt ( "How Many People Are Playing Today? (2-8 Players)",2,8,"Please Enter A Number Between 2-8" );
    }

    public static String playerNames(){
        return Console_input.getString ( "Please Enter Player Name: ", true );
    }

    public static void startingMsg(int cards){
        System.out.println ("Let The Game Begin!! \nAll Players Have Been dealt " + cards + " cards.");
    }

    public static void dealingMsg(int cardCount){
        System.out.println (cardCount+" Cards Have Been Dealt To All The PLayers.");
    }
    public static int getAction(){
        return  Console_input.getInt ("Please Choose One: \n1. Play Card \n2. Draw Card",1,2,"PLease Enter A Number Between 1-2"  );
    }
                                                                //"🟥", "🟦", "🟩", "🟨", "⬛️"
    public static int getColor(){
        return Console_input.getInt ( "Please Choose A Color: \n1. \uD83D\uDFE5 \n2. \uD83D\uDFE6 \n3. \uD83D\uDFE9 \n4. \uD83D\uDFE8", 1,4,"Please Enter A Number 1-4" );
    }
    public static int getPlayCard(Hand activehand){
        int handSize = activehand.getHandSize ();
        return Console_input.getInt ( "Please Enter A Card Number: ", 0,handSize,"Please Enter A Number 1-" + handSize  );
    }
}
