package com.pluralsight;

import java.util.Scanner;

public class Player {

    private Cards cards[] = new Cards[10];

    Scanner keyboard = new Scanner(System.in);
    private int total = 0;
    private int lastTurn;
    private int winLose;

    public Player(){

    }

    public int getNewCard(int cardNum){
        this.cards[cardNum] = new Cards();
        return this.cards[cardNum].getNewCardValue();
    }


    public int getTotal(){
        Cards card;
        for(int i = 0; i < this.cards.length; i++){
            card = this.cards[i];
           if(card != null){
               this.total+= this.cards[i].getCardValue();
           }
        }
        return this.total;
    }

    public void processChoice(int cardNum){
        System.out.println("Would you like to \"hit\" or \"stay\"?");
        String choice = keyboard.next();
        while (!(choice.equalsIgnoreCase("hit") || choice.equalsIgnoreCase("stay"))){
            System.out.println("Entry must be \"hit\" or \"stay\"");
            choice = keyboard.next();
        }

        //If the player chooses to hit
        if (choice.equalsIgnoreCase("hit")){
                int newCard = getNewCard(cardNum);
                System.out.println("You drew a " + newCard);
                this.total += newCard;
                System.out.println("Your new total is " + this.total);
                System.out.println();
                if (this.total == 21){
                    System.out.println("YOU WIN!");
                    setWinLose();

                }
                else if (this.total > 21){
                    System.out.println("You busted.... Dealer wins...");
                    setWinLose();
                }
        }
        //If the player chooses to stay
        else{
            System.out.println("Your total is " + this.total);
            System.out.println();
            setLastTurn();

        }

    }

    public void setLastTurn(){
        this.lastTurn = 1;
    }

    public int getLastTurn(){
        return this.lastTurn;
    }

    public void setWinLose(){
        this.winLose = 1;
    }

    public int getWinLose(){
        return this.winLose;
    }

    public void processDealerChoice(int cardNum){

        System.out.println("Okay, dealer's turn");

        if(cardNum == 2){
            System.out.println("Dealer's hidden card was a " + this.cards[1].getCardValue());
            System.out.println("Dealer's total was " + this.total);
        }

        String choice;
        if (this.total <= 16){
            choice = "hit";
        }
        else {
            choice = "stay";
        }

        System.out.println("Dealer chose to " + choice);

        //If the dealer hits
        if (choice.equalsIgnoreCase("hit")){
            int newCard = getNewCard(cardNum);
            System.out.println("Dealer drew a " + newCard);
            this.total += newCard;
            System.out.println("Dealer's new total is " + this.total);
            System.out.println();
            if (this.total == 21){
                System.out.println("Dealer wins....");
                setWinLose();
            }
            else if (this.total > 21){
                System.out.println("DEALER BUSTED! YOU WIN!");
                setWinLose();
            }
        }
        //If the dealer stays
        else{
            System.out.println("Dealers total is " + this.total);
            setLastTurn();
        }

    }


}
