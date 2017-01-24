package app.app;

import app.app.CommunityCard;
import app.app.OldCard;
import app.app.Dealer;
import app.app.PlayerCard;
import app.app.ComputerCard;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lorin
 */
public class Table extends JFrame{
    
    private Dealer dealer;
    
    private final String[] RANK = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private final String[] SUIT = {"club", "diamond", "heart", "spade"};
    
    //Array to load all the images
    ArrayList<ImageIcon> allImages = new ArrayList<>(); 
    
    //Array to store all the cards
    ArrayList<PlayerCard> allTheCards;

    
    //ArrayList for computer players 
    ArrayList<PlayerCard> player1 = new ArrayList<>();
    ArrayList<PlayerCard> player2 = new ArrayList<>();
    ArrayList<PlayerCard> player3 = new ArrayList<>();
    
    //ArrayList for human player
    static ArrayList<PlayerCard> humanPlayerHand = new ArrayList<>(); 
    
    //ArrayList of players AL
    ArrayList<ArrayList<PlayerCard>> allPlayers = new ArrayList<>();
            
    //ArrayList for faceCard
    static ArrayDeque<PlayerCard> faceCardArray = new ArrayDeque<>();
        
    //ArrayDeque for ComunityCards
    ArrayDeque<PlayerCard> communityCardsArray=  new ArrayDeque<>();
    
    
    //static GameRules game = new GameRules();
    
    public static void main(String[] args) {
        
        Table t = new Table();       

        int playerX = 200;
        
        for(PlayerCard card: humanPlayerHand){
            card.setIcon(card.getCardIcon());
            card.setLocation(playerX, 400);
            playerX += 110;            
            t.add(card);
        }
        
        //game.playerClickHisCard(faceCard, humanPlayerHand);
        
    }
    
    public Table(){        
        this.setLayout(null);        
        
        //add images to to -allImages- ArrayList
        for(int i=0; i<52; i++){
            
            String name = "C:\\Users\\Lorin\\Documents\\NetBeansProjects\\Choices\\App\\src\\app\\images\\" + i + ".png";
                       
            try{
                ImageIcon bigImage = new ImageIcon(name);                
                allImages.add(bigImage);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        
        //instantiate dealer
        dealer = new Dealer();
        
        //create and add all the cards to the array
        allTheCards = dealer.createAllTheTouchCards(RANK, SUIT, allImages, faceCardArray, humanPlayerHand);// 
        
        //add players array to one array
        allPlayers.add(player1);
        allPlayers.add(player2);
        allPlayers.add(player3);
        
        allPlayers.add(humanPlayerHand);
        
        //deal the cards
        dealer.deal(allTheCards, faceCardArray, allPlayers, communityCardsArray);

        //======================================================================
        //TODO - create TouchCard interface/abstract class
        //======================================================================        
        
        //create CardtoCommunityCard for comunityCard
        CommunityCard communityCard = new CommunityCard(faceCardArray, humanPlayerHand);
        
        //create CardtoFaceCard  for faceCArd
        FaceCard faceCard = new FaceCard(faceCardArray);
        
        //create ComputerPlayerTouchCard for computer1**************************       
        ComputerCard computer1 = new ComputerCard();               
        computer1.setLocation(0, 200);
        
        //create ComputerPlayerTouchCard for computer2        
        ComputerCard computer2 = new ComputerCard();         
        computer2.setLocation(450, 0);
        
        //create ComputerPlayerTouchCard for computer3   
        ComputerCard computer3 = new ComputerCard();
        computer3.setLocation(870, 150); 
        
        this.add(communityCard);
        this.add(faceCard);
        this.add(computer1);
        this.add(computer2);
        this.add(computer3);
        
        this.setSize(1000, 600);
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        this.setVisible(true);
    }
    
}
