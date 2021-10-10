package com.company.utilites;

public abstract class Random_num {

    public static int num(int min, int max){

         int value = (int) Math.floor(Math.random()*(max-min+1)+min);

        return value;

    }


}
