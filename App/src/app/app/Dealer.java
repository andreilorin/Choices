package app.app;

import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Represents the dealer
 * @author Lorin
 */
public class Dealer {    
   
    /**
     * Creates all the cards in the game with icons
     * @param rank
     * @param suit
     * @param allImages
     * @param faceCard
     * @param humanPlayerHand
     * @return 
     */
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
