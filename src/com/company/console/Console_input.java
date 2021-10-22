package com.company.console;
import java.util.Scanner;

import static java.lang.System.out;

public  abstract class Console_input {

    private static final Scanner scanner = new Scanner(System.in);

    protected static int getInt(String prompt, int min, int max, String errorMsg) {
        int option = min - 1;
        do {
            System.out.println(prompt);
            String input = scanner.next();

            try{
                option = Integer.parseInt(input);
            } catch (NumberFormatException err) {
                System.out.println(errorMsg);
            }
        } while (option < min || option > max);
        return option;
    }

    protected static String getString(String prompt, boolean isRequired) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine();
            if (isRequired && input.length() == 0) {
                System.out.println("Entry Required");
                continue;
            }
            break;
        } while (true);
        return input;
    }

    protected static boolean yesNO(){

        while(true) {
          String  yn = scanner.next ();
           switch (yn) {
               case "Y", "y", "Yes", "yes" -> {return true;}
               case "N", "n", "No", "no" -> {return false;}
               default -> {out.println ( "Please Enter Yes or No" );}
           }
       }
    }
    

}
