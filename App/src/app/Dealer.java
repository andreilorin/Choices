package app;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Dealer {    
    /**
     * Creates a card with rank and suit
     * @param rank
     * @param suit
     * @return 
     */
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
    /**
     * Creates a card with image
     * @param rank
     * @param suit
     * @param allImages
     * @return 
     */
    public ArrayList<Card> createAllTheCards(String[] rank, String[] suit, ArrayList<BufferedImage> allImages){
        
        ArrayList<Card> allTheCards = new ArrayList<>();       
        int cardNumber = 0;
        for(int j=0; j<suit.length; j++){
            for(int i=0; i<rank.length; i++){            
                Card card = new Card(rank[i], suit[j], allImages.get(cardNumber));
                allTheCards.add(card);
                cardNumber++;
            }            
        }
        
        return allTheCards;
    }
    
    public void deal(ArrayList<Card> allTheCards, ArrayList<Card> faceCard, ArrayList<ArrayList<Card>> players){          
        
        for(int i=0; i<5; i++){
            for (ArrayList<Card> player : players) {                
                int random = (int)(Math.random() * allTheCards.size()); 
                Card card = allTheCards.get(random);        
                player.add(card);
                allTheCards.remove(random);
            }
        }
        int random = (int)(Math.random() * allTheCards.size()); 
        Card card = allTheCards.get(random);
        faceCard.add(card);
        allTheCards.remove(random);
    }
    
}
