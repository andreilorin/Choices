package app;

import java.util.ArrayList;

public class Dealer {    
    
    public ArrayList<Card> createAllTheCards(String[] rank, String[] suit){
        
        ArrayList<Card> allTheCards = new ArrayList<>();       
        
        for(int j=0; j<suit.length; j++){
            for(int i=0; i<rank.length; i++){            
                Card card = new Card(rank[i], suit[j]);
                allTheCards.add(card);
                
            }            
        }
        
        return allTheCards;
    }
    
    public void deal(ArrayList<Card> allTheCards, ArrayList<Card> faceCard, ArrayList<ArrayList<Card>> players){
        
        int random = (int)(Math.random() * allTheCards.size());    
        
        for(int i=0; i<5; i++){
            for (ArrayList<Card> player : players) {                
                Card card = allTheCards.get(random);        
                player.add(card);
                allTheCards.remove(random);
            }
        }
        Card card = allTheCards.get(random);
        faceCard.add(card);
    }
    
}
