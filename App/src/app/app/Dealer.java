package app.app;

import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import app.app.OldCard;

public class Dealer {    
    /**
     * Creates an ArrayList of cards
     * @param rank
     * @param suit
     * @return 
     */
    public ArrayList<OldCard> createAllTheCards(String[] rank, String[] suit){
        
        ArrayList<OldCard> allTheCards = new ArrayList<>();       
        
        for(int j=0; j<suit.length; j++){
            for(int i=0; i<rank.length; i++){            
                OldCard card = new OldCard(rank[i], suit[j]);
                allTheCards.add(card);                
            }            
        }        
        return allTheCards;
    }
    
    /**
     * Creates an ArrayList of cards with image
     * @param rank
     * @param suit
     * @param allImages
     * @return
     */
    public ArrayList<OldCard> createAllTheCards(String[] rank, String[] suit, ArrayList<ImageIcon> allImages){
        
        ArrayList<OldCard> allTheCards = new ArrayList<>();       
        int cardNumber = 0;
        for(int j=0; j<suit.length; j++){
            for(int i=0; i<rank.length; i++){            
                OldCard card = new OldCard(rank[i], suit[j], allImages.get(cardNumber));
                allTheCards.add(card);
                cardNumber++;
            }            
        }        
        return allTheCards;
    }
    
    //Create touchcard list
    public ArrayList<PlayerCard> createAllTheTouchCards(String[] rank, String[] suit, ArrayList<ImageIcon> allImages,
            ArrayDeque<PlayerCard> faceCard, ArrayList<PlayerCard> humanPlayerHand){
        
        ArrayList<PlayerCard> allTheCards = new ArrayList<>();       
        int cardNumber = 0;
        for(int j=0; j<suit.length; j++){
            for(int i=0; i<rank.length; i++){            
                PlayerCard card = new PlayerCard(rank[i], suit[j], allImages.get(cardNumber), faceCard, humanPlayerHand);
                allTheCards.add(card);
                cardNumber++;
            }            
        }        
        return allTheCards;
    }
    
    /**
     * Deals the cards
     * @param allTheCards
     * @param faceCard
     * @param players
     * @param deck
     */
    public void deal(ArrayList<PlayerCard> allTheCards, ArrayDeque<PlayerCard> faceCard, ArrayList<ArrayList<PlayerCard>> players, ArrayDeque<PlayerCard>deck){          
        
        for(int i=0; i<5; i++){
            for (ArrayList<PlayerCard> player : players) {                
                int random = (int)(Math.random() * allTheCards.size()); 
                PlayerCard tc = allTheCards.get(random);
                //Card card = tc.getTheCard();
                player.add(tc);
                allTheCards.remove(random);
            }
        }
        int random = (int)(Math.random() * allTheCards.size()); 
        PlayerCard card = allTheCards.get(random);
        faceCard.add(card);
        allTheCards.remove(random);
        
        for(int j=0; j < allTheCards.size(); j++){
            deck.add(allTheCards.get(j));
        }
    }
    
}
