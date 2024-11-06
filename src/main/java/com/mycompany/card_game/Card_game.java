package com.mycompany.card_game;

import com.mycompany.card_game.card.Card;
import com.mycompany.card_game.player.Player;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author aparup
 */
public class Card_game {
    static int random(int s, int e) {
        double n = Math.random();
        n = Math.floor(n * 10);
        while (n < s || n >= e) {
            n = Math.random();
            n = Math.floor(n * 10);
        }
        return (int) n;
    }

    static Card[] createAndShuffleCards() {
        // make 4 card decks of different types
        ArrayList<Card> hearts = new ArrayList<>();
        ArrayList<Card> diamonds = new ArrayList<>();
        ArrayList<Card> spades = new ArrayList<>();
        ArrayList<Card> clubs = new ArrayList<>();

        // Put the data in each decks
        for (int i = 0; i <= 12; i++) {
            hearts.add(new Card(i + 2, "hearts"));
            diamonds.add(new Card(i + 2, "diamonds"));
            spades.add(new Card(i + 2, "spades"));
            clubs.add(new Card(i + 2, "clubs"));
        }

        // Create main deck
        Card[] deck = new Card[52];

        // Shuffle the cards: randomly pick a card from different decks
        // and put it in the main deck
        ArrayList<ArrayList<Card>> deckOfTypes = new ArrayList<>();
        deckOfTypes.add(hearts);
        deckOfTypes.add(diamonds);
        deckOfTypes.add(spades);
        deckOfTypes.add(clubs);
        // Now we have arraylist of size 4 each element of which is a deck of a type

        int mainDeckIndex = 0;
        while (!deckOfTypes.isEmpty()) {
            // Randomly pick a different type from the deck of types
            int randomDeckIndex = random(0, deckOfTypes.size());

            // Randomly pick a card from the type
            int randomCardIndex = random(0, deckOfTypes.get(randomDeckIndex).size());

            // Add the picked card to the main deck
            deck[mainDeckIndex] = deckOfTypes.get(randomDeckIndex).get(randomCardIndex);

            // After picking the card remove it from the deck
            deckOfTypes.get(randomDeckIndex).remove(randomCardIndex);

            // If the randomly picked types doesn't have any more card
            // remove it from the deck of cards
            if (deckOfTypes.get(randomDeckIndex).isEmpty()) {
                deckOfTypes.remove(randomDeckIndex);
            }
            mainDeckIndex++;
        }

        return deck;
    }

    static int toss() {
        return random(1, 3);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Intro
        System.out.println("_____ CARD WAR ____\n");
        System.out.println("(i) Press ENTER to proceed!");
        sc.nextLine();

        // Create a player

        System.out.print("Enter player name: ");
        String playerName = sc.nextLine();

        Player p1 = new Player(playerName);
        Player p2 = new Player("COMPUTER");

        // Create and shuffle the deck of cards
        Card[] deck = createAndShuffleCards();

        // Divide the cards into two players
        for(int i=0; i<51; i+=2){
            p1.addPlayingCard(deck[i]);
            p2.addPlayingCard(deck[i+1]);
        }


        // Toss to select the player's turn
        int turn = toss();


        Stack<Card> playPile=new Stack<>();
        Card p1CurrentCard;
        Card p2CurrentCard;
        int round=1;
        System.out.println();
        while(round<=26) {
            System.out.println("_____ Round " + round + " _____");
            if (turn == 1) {
                p1CurrentCard = p1.getCurrentCard();
                System.out.print("(" + p1.getScore() + ") " + p1.getName() + "'s turn: " + p1CurrentCard);
                sc.nextLine();
                playPile.push(p1CurrentCard);
                System.out.println("Play pile: " + playPile);
                System.out.println();

                p2CurrentCard = p2.getCurrentCard();
                System.out.print("(" + p2.getScore() + ") " + p2.getName() + "'s turn: " + p2CurrentCard);
                sc.nextLine();
                playPile.push(p2CurrentCard);
                System.out.println("Play pile: " + playPile);
                System.out.println();
            } else {
                p2CurrentCard = p2.getCurrentCard();
                System.out.print("(" + p2.getScore() + ") " + p2.getName() + "'s turn: " + p2CurrentCard);
                sc.nextLine();
                playPile.push(p2CurrentCard);
                System.out.println("Play pile: " + playPile);
                System.out.println();

                p1CurrentCard = p1.getCurrentCard();
                System.out.print("(" + p1.getScore() + ") " + p1.getName() + "'s turn:" + p1CurrentCard);
                sc.nextLine();
                playPile.push(p1CurrentCard);
                System.out.println("Play pile: " + playPile);
                System.out.println();
            }

            // Playing logic


            // If draw then push the cards on the play pile
            if (p1CurrentCard.getRank() == p2CurrentCard.getRank()) {
                System.out.println("Drawn!");
                turn = turn == 1 ? 2 : 1;
            } else if (p1CurrentCard.getRank() > p2CurrentCard.getRank()) {
                System.out.println(p1.getName() + " won the round!");
                while (!playPile.isEmpty()) {
                    p1.addWonCard(playPile.pop());
                }
                p1.setScore(p1.getScore() + 1);
                turn = 1;
            } else {
                System.out.println(p2.getName() + " won the round!");
                while (!playPile.isEmpty()) {
                    p2.addWonCard(playPile.pop());
                }
                p2.setScore(p2.getScore() + 1);
                turn = 2;
            }
            round++;

            System.out.println("-------x-------\n");
        }

        Player winner=p1.getScore()>p2.getScore()?p1:p2;
        Player looser=p1.getScore()>p2.getScore()?p2:p1;
        System.out.println("______ GAME FINISHED ______");
        System.out.println(winner.getName()+" won the game!\n");
        System.out.println("______ SCORE BOARD ______");
        System.out.println(winner.getName() + "(W): " + winner.getScore());
        System.out.println(looser.getName() + ": " + looser.getScore());
    }
}
