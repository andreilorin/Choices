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
    public void deal(ArrayList<PlayerCard> allTheCards, ArrayDeque<PlayerCard> faceCard,
            ArrayList<ArrayList<PlayerCard>> players, ArrayDeque<PlayerCard>deck){          
        
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
    
    public static void refillCommunityCards(ArrayDeque<PlayerCard> communityCardsArray,
        ArrayDeque<PlayerCard> faceCardArray){
                
        ArrayList<PlayerCard> swapList = new ArrayList<>();
             
        
        if(communityCardsArray.isEmpty()){
            
            System.out.println("refilling community cards");
            for(int i=0; i<faceCardArray.size() - 1 ; i++){
                swapList.add(faceCardArray.getFirst());
            }
            
            for(int i=0; i<swapList.size() - 1 ; i++){
                int random = (int)(Math.random() * swapList.size()); 
                communityCardsArray.add(swapList.get(random));
            }
        }
    }
    
    public static void findWinner(ArrayList<PlayerCard> computer1hand, ArrayList<PlayerCard> computer2hand,
            ArrayList<PlayerCard> computer3hand, ArrayList<PlayerCard> playerHand){
        
        if(computer1hand.isEmpty()){
            System.out.println("Player1 wins");
        }
        else if(computer2hand.isEmpty()){
            System.out.println("Player2 wins");
        }
        else if(computer3hand.isEmpty()){
            System.out.println("Player3 wins");
        }
        else if(playerHand.isEmpty())
            System.out.println("You Win !");
    }
}
