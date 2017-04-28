package computerai;

import cards.PlayerCard;
import java.util.Iterator;
import table.Dealer;
import table.Table;

public class ComputerAI {
    
    //The computerAI method uses Iterator to prevent ConcurrentModificationException
    //and instead of an else statement I used a check to decide if the computer player
    //needs to draw a card from Community, to prevent the same exception
    public static void move(Table table){
           
        //Computer1 
        int computer1handSizeBefore = table.computer1hand.size();
        Iterator<PlayerCard> iterator1 = table.computer1hand.iterator();
        
        while(iterator1.hasNext()){
            PlayerCard card1 = iterator1.next();
            if(card1.getRank().equals(table.faceCardArray.getLast().getRank()) || 
                    card1.getSuit().equals(table.faceCardArray.getLast().getSuit())){                
                table.faceCard.setIcon(card1.getCardIcon());
                table.faceCardArray.add(card1);                
                table.computer1hand.remove(card1);                
                break;
            }
        }
        
        table.dealer.checkForWinner(table.computer1hand, "Computer1 Wins", "YOU LOST !");
        
        int computer1handSizeAfter = table.computer1hand.size();
        
        if(computer1handSizeBefore == computer1handSizeAfter){
            table.computer1hand.add(table.communityCardsArray.pollFirst());            
        }
        Dealer.refillCommunityCards(table.communityCardsArray, table.faceCardArray);
        System.out.println(table.computer1hand.size() + " C1");
        
        //Computer2
        int computer2handSizeBefore = table.computer2hand.size();
        Iterator<PlayerCard> iterator2 = table.computer2hand.iterator();
        
        while(iterator2.hasNext()){
            PlayerCard card2 = iterator2.next();
            if(card2.getRank().equals(table.faceCardArray.getLast().getRank()) || 
                    card2.getSuit().equals(table.faceCardArray.getLast().getSuit())){                
                table.faceCard.setIcon(card2.getCardIcon());
                table.faceCardArray.add(card2);                
                table.computer2hand.remove(card2);                
                break;
            }
        }
        
        table.dealer.checkForWinner(table.computer2hand, "Computer2 Wins", "YOU LOST !");
        
        int computer2handSizeAfter = table.computer2hand.size();
        
        if(computer2handSizeBefore == computer2handSizeAfter){
            table.computer2hand.add(table.communityCardsArray.pollFirst());            
        }
        Dealer.refillCommunityCards(table.communityCardsArray, table.faceCardArray);
        System.out.println(table.computer2hand.size() + " C2");
        
        //Computer3
        int computer3handSizeBefore = table.computer3hand.size();
        Iterator<PlayerCard> iterator3 = table.computer3hand.iterator();
        
        while(iterator3.hasNext()){
            PlayerCard card3 = iterator3.next();
            if(card3.getRank().equals(table.faceCardArray.getLast().getRank()) || 
                    card3.getSuit().equals(table.faceCardArray.getLast().getSuit())){                
                table.faceCard.setIcon(card3.getCardIcon());
                table.faceCardArray.add(card3);                
                table.computer3hand.remove(card3);                
                break;
            }
        }
        
        table.dealer.checkForWinner(table.computer3hand, "Computer3 Wins", "YOU LOST !");
        
        int computer3handSizeAfter = table.computer3hand.size();
        
        if(computer3handSizeBefore == computer3handSizeAfter){
            table.computer3hand.add(table.communityCardsArray.pollFirst());            
        }
        Dealer.refillCommunityCards(table.communityCardsArray, table.faceCardArray);
        System.out.println(table.computer3hand.size() + " C3");
    }
    
}
