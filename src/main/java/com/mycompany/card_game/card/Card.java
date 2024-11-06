package com.mycompany.card_game.card;
import java.util.HashMap;
/**
 *
 * @author aparup
 */
public class Card {
    private int rank;
    private String number;
    private String type;

    HashMap<Integer, String> rankToNum=new HashMap<>();
    
    {
        for(int i=2; i<=10; i++){
            rankToNum.put(i,Integer.toString(i));
        }
        rankToNum.put(11, "J");
        rankToNum.put(12, "Q");
        rankToNum.put(13, "K");
        rankToNum.put(14, "A");  
    }
    
    
    public Card(String number, String type){
        this.number=number;
        this.type=type;
    }
    
    public Card(int rank, String type){
        this.rank=rank;
        this.number=rankToNum.get(rank);
        this.type=type;
    }
    
    public int getRank(){
        return this.rank;
    }
    
    public String getNumber(){
        return this.number;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String toString(){
        return "(" + this.number + ", " + this.type + ")";
    }
}
