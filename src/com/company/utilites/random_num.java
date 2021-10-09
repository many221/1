package com.company.utilites;

public abstract class random_num {

    public static int num(int min, int max){

         int value = (int) Math.floor(Math.random()*(max-min+1)+min);

        return value;

    }


}
