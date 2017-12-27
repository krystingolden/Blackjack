package com.pluralsight;

import java.util.Random;

public class Cards {

    private int cardValue;
    Random r = new Random();

    public Cards(){

    }


    public int getCardValue(){
        return this.cardValue;
    }

    public int getNewCardValue(){
        this.cardValue = 2 + r.nextInt(9);
        return this.cardValue;
    }
}
