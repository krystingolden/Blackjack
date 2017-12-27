package com.pluralsight;

public class Main {

/*
https://programmingbydoing.com/

Blackjack - Assignment 76
*/

    public static void main(String[] args) {

        //Display initial prompts and retrieve initial cards for both players
        System.out.println("Welcome to Blackjack!");

        playGame();

    }

    public static void playGame(){
        Player player1 = new Player();
        System.out.println("You get a " + player1.getNewCard(0) + " and a " + player1.getNewCard(1));
        System.out.println("Your total is " + player1.getTotal());
        System.out.println();
        Player dealer = new Player();
        System.out.println("The dealer has a " + dealer.getNewCard(0) + " and a hidden card");
        dealer.getNewCard(1);
        System.out.println("His total is hidden too");
        System.out.println();
        dealer.getTotal();

        //Play the game as long as it's not the last turn or a hard win/lose
        int cardNum = 2;
        int keepPlaying = 0;
        while (keepPlaying == 0){

            player1.processChoice(cardNum);

            keepPlaying = player1.getLastTurn() + player1.getWinLose();

            if (player1.getWinLose() == 0){
                dealer.processDealerChoice(cardNum);
                if(player1.getLastTurn()== 1){
                    dealer.setLastTurn();
                }
                keepPlaying = dealer.getLastTurn() + dealer.getWinLose() + keepPlaying;
            }
            cardNum++;
        }

        //If it's the last turn for one player, run the other players last turn if they haven't chosen to stay
        if (player1.getWinLose() == 0 && dealer.getWinLose() == 0) {
            if (player1.getLastTurn() == 1 && dealer.getLastTurn() == 0) {
                cardNum++;
                dealer.processDealerChoice(cardNum);
            } else if (player1.getLastTurn() == 0 && dealer.getLastTurn() == 1) {
                cardNum++;
                player1.processChoice(cardNum);
            }
        }

        //If both players have stayed, determine which player wins based on highest score
        if (player1.getWinLose() == 0 && dealer.getWinLose() == 0) {
            if (player1.getTotal() < dealer.getTotal()) {
                System.out.println("Dealer had a higher total. Dealer wins.");
            } else if (player1.getTotal() == dealer.getTotal()) {
                System.out.println("Tie score. Dealer wins");
            } else {
                System.out.println("You had a higher total. YOU WIN!");
            }
        }
    }
}
