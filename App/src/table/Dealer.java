package table;

import cards.PlayerCard;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Dealer {
        
    public static int roundNumber = 2;
   
    public ArrayList<PlayerCard> createAllPlayerCards(String[] rank, String[] suit,
            ArrayList<ImageIcon> allImages, Table table){
        
        ArrayList<PlayerCard> allTheCards = new ArrayList<>();       
        int cardNumber = 0;
        for(int j=0; j<suit.length; j++){
            for(int i=0; i<rank.length; i++){            
                PlayerCard card = new PlayerCard(rank[i], suit[j], allImages.get(cardNumber), table);
                allTheCards.add(card);
                cardNumber++;
            }            
        }        
        return allTheCards;
    }
  
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
             
        if(communityCardsArray.size() == 1){
            
            System.out.println("fill swap");
            Iterator<PlayerCard> iterator1 = faceCardArray.iterator();
            while(iterator1.hasNext()){
                PlayerCard card1 = iterator1.next();
                swapList.add(card1);
            }
            System.out.println("swap list size : " + swapList.size());
            
            Iterator<PlayerCard> iterator2 = faceCardArray.iterator();
            while(iterator2.hasNext()){
                PlayerCard card2 = iterator2.next();
                communityCardsArray.add(card2);
            }
            System.out.println("comm card new size " + communityCardsArray.size());
        }
    }
    
    public void checkForWinner(Collection hand, String message, String winner, Table table ){
        if(hand.isEmpty()){
            JOptionPane.showMessageDialog(null, winner, message, JOptionPane.INFORMATION_MESSAGE);
            table.endGame(true);
            
            if(!table.isPlaying()) {
                table.removeDummyCards();
            }
            
        }        
    }
    
    public String updateCardNumbers(Table table){
        int firstPlayer = table.computer1hand.size();
        int secondPlayer = table.computer2hand.size();
        int thirdlayer = table.computer3hand.size();
        
        return "\nComputer1:" + firstPlayer + " Computer2:" + secondPlayer + " Computer3:" + thirdlayer;
    }
}
