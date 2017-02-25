package app.app;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Game class. This is where the game runs
 * @author Lorin
 */
public class Table extends JFrame{
    
    //Dealer
    public Dealer dealer;
    
    //Computer player hands
    ComputerCard computer1;
    ComputerCard computer2;
    ComputerCard computer3;
    
    //assistant
    Assistant assistant;
    
    //field to display image of the face card
    FaceCard faceCard;
   
    //Array to load all the images
    ArrayList<ImageIcon> allImages = new ArrayList<>(); 
    
    //Array to store all the cards
    ArrayList<PlayerCard> allTheCards;
    
    //ArrayList for computer players 
    ArrayList<PlayerCard> computer1hand = new ArrayList<>();
    ArrayList<PlayerCard> computer2hand = new ArrayList<>();
    ArrayList<PlayerCard> computer3hand = new ArrayList<>();
    
    //ArrayList for human player
    ArrayList<PlayerCard> playerHand = new ArrayList<>(); 
    
    //ArrayList of players AL
    ArrayList<ArrayList<PlayerCard>> allPlayers = new ArrayList<>();
            
    //ArrayList for faceCard
    ArrayDeque<PlayerCard> faceCardArray = new ArrayDeque<>();
        
    //ArrayDeque for ComunityCards
    ArrayDeque<PlayerCard> communityCardsArray =  new ArrayDeque<>();
    
    //main method    
    public static void main(String[] args) {        
        new Table();
    }
    
    /**
     * Constructor
     * Sets up the table and the cards
     */
    public Table(){   
               
        this.setLayout(null);        
        
        //add images to to allImages ArrayList
        for(int i=0; i<52; i++){
            
            String name = "src\\app\\images\\" + i + ".png";
                       
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
        allTheCards = dealer.createAllPlayerCards(Card.RANK, Card.SUIT, 
                allImages, faceCardArray, playerHand, this);// 
        
        //add players array to one array
        allPlayers.add(computer1hand);
        allPlayers.add(computer2hand);
        allPlayers.add(computer3hand);
        
        allPlayers.add(playerHand);
        
        //deal the cards
        dealer.deal(allTheCards, faceCardArray, allPlayers, communityCardsArray);           
                    
        //create button for rearenge
        JButton rearangeCardsButton = new JButton("Rearange");
        rearangeCardsButton.setLocation(0, 0);
        rearangeCardsButton.setSize(150, 50);
        rearangeCardsButton.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                rearrangeCards();                
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });        
        rearangeCardsButton.setVisible(true);        
        this.add(rearangeCardsButton);
        
        //create second button
        JButton rearangeCards2 = new JButton("Assistant");
        rearangeCards2.setLocation(200, 0);
        rearangeCards2.setSize(150, 50);        
        rearangeCards2.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                assistant = new Assistant(Table.this);
                assistant.run();
                assistant.textArea.setText(null);
                LocalDateTime timePoint = LocalDateTime.now();
                assistant.textArea.append("\n" + timePoint.getHour() + ":" + timePoint.getMinute() + ":" + timePoint.getSecond() +
                        "| Round " + (Dealer.getRoundNumber() - 1) + ": click on " +  
                        faceCardArray.getLast().getRank() + " or " + faceCardArray.getLast().getSuit());                                
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}            
        });        
        rearangeCards2.setVisible(true);        
        this.add(rearangeCards2);
        
        //add all the cards on the table        
        placeDummyCards();
        
        placeCardsOnTable();
        
        //frame settings
        this.setSize(1000, 800);
        this.setLocation(500, 200);
        this.setTitle("Choices");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        this.setVisible(true); 
        
        assistant = new Assistant(this);
        assistant.run();
    }
    
    /**
     * Adds all the cards from the humanPlayerHand array to the table at their 
     * place
     */    
    public void placeCardsOnTable(){        
        int playerX = 700;
        int location = 600/playerHand.size(); 
        
        for(PlayerCard card: playerHand){
            card.setIcon(card.getCardIcon());           
            card.setLocation(playerX, 400);  
            playerX -= location;
            this.add(card);
        }
        
        for(PlayerCard card: faceCardArray){
            card.setIcon(faceCardArray.getLast().getCardIcon());
            card.setLocation(Card.FACECARDLOCATION);
            this.add(card);
        }
        
        for(PlayerCard card: computer1hand){
            card.setIcon(card.getCardIcon());
            card.setLocation(Card.COMPUTER1LOCATION);
            this.add(card);
        }
        
        for(PlayerCard card: computer2hand){
            card.setIcon(card.getCardIcon());
            card.setLocation(Card.COMPUTER2LOCATION);
            this.add(card);
        }
        
        for(PlayerCard card: computer3hand){
            card.setIcon(card.getCardIcon());
            card.setLocation(Card.COMPUTER3LOCATION);
            this.add(card);
        }
        
        for(PlayerCard card: communityCardsArray){
            card.setIcon(card.getCardIcon());
            card.setLocation(Card.COMMUNITYDCARDLOCATION);
            this.add(card);
        }       
    }
    
    public void placeDummyCards(){
       //create ComputerCard for computer2        
        computer1 = new ComputerCard();         
        computer1.setLocation(Card.COMPUTER1LOCATION);
        
        //create ComputerCard for computer2        
        computer2 = new ComputerCard();         
        computer2.setLocation(Card.COMPUTER2LOCATION);
        
        //create ComputerCard for computer3   
        computer3 = new ComputerCard();
        computer3.setLocation(Card.COMPUTER3LOCATION);
        
        //create CommunityCard
        CommunityCard communityCard = new CommunityCard(this, faceCardArray, 
                playerHand, communityCardsArray);
        
        //create FaceCard
        faceCard = new FaceCard(faceCardArray);
        
        //add communityCard and computer cards to the table
        this.add(computer1);        
        this.add(computer2);
        this.add(computer3);
        this.add(communityCard);
        this.add(faceCard);
    }
    
    /**
     * Rearranges the cards on the table(they can get rearranged in random order
     * but the cards keep their order in the array they belong to)
     */
    public void rearrangeCards(){        
        int playerX = 700;
        int distance = 5;
        
        try{
            distance = 600/playerHand.size(); 
        
        
            for(PlayerCard card: playerHand){          
                card.setLocation(playerX, 400);  
                playerX -= distance;           
            }
       
            for(PlayerCard card: faceCardArray){            
                card.setLocation(Card.FACECARDLOCATION);
            }
        
            for(PlayerCard card: computer1hand){
                card.setLocation(Card.COMPUTER1LOCATION);
            }
        
            for(PlayerCard card: computer2hand){
                card.setLocation(Card.COMPUTER2LOCATION);
            }
        
            for(PlayerCard card: computer3hand){            
                card.setLocation(Card.COMPUTER3LOCATION);
            }
        
            for(PlayerCard card: communityCardsArray){            
                card.setLocation(Card.COMMUNITYDCARDLOCATION);
            }
        }catch(ArithmeticException e){
            System.out.println("exception player has no more cards to rearrange");
        }catch(NullPointerException ex){
            System.out.println("null pointer, no card in player hand to place");
        }
    }
}
