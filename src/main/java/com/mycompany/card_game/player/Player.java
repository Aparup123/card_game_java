package com.mycompany.card_game.player;

import com.mycompany.card_game.card.Card;
import java.util.Stack;
/**
 *
 * @author aparup
 */
public class Player {
    private String name;
    private int score;
    private Stack<Card> playingCards;
	
    private Stack<Card> wonCards;
    
    public Player(String name){
        this.name=name;
        this.score=0;
        this.playingCards=new Stack<>();
        this.wonCards=new Stack<>();
    }
    
    public String getName(){
	return this.name;
    }
    
    public int getScore(){
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addPlayingCard(Card card){
        this.playingCards.push(card);
    }
    
    public void addWonCard(Card card){
        this.wonCards.push(card);
    }
    
    public Stack<Card> getWonCards(){
	return wonCards;
    }
    public Stack<Card> getPlayingCards(){
        return playingCards;
    }
    
    public Card getCurrentCard(){
	return playingCards.pop();
    }
}
