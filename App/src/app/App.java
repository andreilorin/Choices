package app;

import java.util.ArrayList;

/**
 *
 * @author Lorin
 */
public class App {

    private static final String[] 
            rank = {"2", "3", "4", "5", "6", "7", 
                "8", "9", "10", "J", "Q", "K", "A"},
            suit = {"club", "diamond", "heart", "spade"};   
      
    
    
    public static void main(String[] args) {
        ArrayList<Card> p1 = new ArrayList<>();
        ArrayList<Card> p2 = new ArrayList<>();
        ArrayList<Card> p3 = new ArrayList<>();
         ArrayList<Card> faceCard = new ArrayList<>();
        
        
        ArrayList<ArrayList<Card>> players = new ArrayList<>();
        
        players.add(p1);
        players.add(p2);
        players.add(p3);
        
        Dealer dealer = new Dealer();
        ArrayList<Card> allTheCards = dealer.createAllTheCards(rank, suit);
        
//        for (int i=0; i<52; i++) {
//            System.out.println("" + (i+1) + "   " + allTheCards.get(i).getRankSuit());
//        }
        
        int i = 0;
        for (Card c : allTheCards) {
            System.out.println("" + i + " " +c.getRankSuit());
            i++;
        }
        
        
        System.out.println(allTheCards.get(15).getValue());
        
        dealer.deal(allTheCards, faceCard, players);
        
        for (Card c : p3) {
            System.out.println(c.getRankSuit());            
        }
        
        System.out.println(faceCard.get(0).getRankSuit());
        
    }
    
}
