package app.app;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Game class. This is where the game runs
 * @author Lorin
 */
public class Table extends JFrame{
    
    private Dealer dealer;
    
    //field to display image of the face card
    public FaceCard faceCard;
    
    //field for text Area
    JTextArea textArea;
   
    //Array to load all the images
    ArrayList<ImageIcon> allImages = new ArrayList<>(); 
    
    //Array to store all the cards
    ArrayList<PlayerCard> allTheCards;
    
    //ArrayList for computer players 
    ArrayList<PlayerCard> computer1hand = new ArrayList<>();
    ArrayList<PlayerCard> computer2hand = new ArrayList<>();
    ArrayList<PlayerCard> computer3hand = new ArrayList<>();
    
    //ArrayList for human player
    static ArrayList<PlayerCard> playerHand = new ArrayList<>(); 
    
    //ArrayList of players AL
    ArrayList<ArrayList<PlayerCard>> allPlayers = new ArrayList<>();
            
    //ArrayList for faceCard
    static ArrayDeque<PlayerCard> faceCardArray = new ArrayDeque<>();
        
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
        
        //FaceCard faceCard = new FaceCard(faceCardArray);
        
        //create and add all the cards to the array
        allTheCards = dealer.createAllTheTouchCards(Card.RANK, Card.SUIT, 
                allImages, faceCardArray, playerHand, this);// 
        
        //add players array to one array
        allPlayers.add(computer1hand);
        allPlayers.add(computer2hand);
        allPlayers.add(computer3hand);
        
        allPlayers.add(playerHand);
        
        //deal the cards
        dealer.deal(allTheCards, faceCardArray, allPlayers, communityCardsArray);           
                
        //add text area       
        textArea = new JTextArea();            
        textArea.setLocation(300, 600);
        textArea.setSize(400, 100);
        textArea.setVisible(true);
        textArea.setEditable(true);
        this.add(textArea);
        
        //create button for rearenge
        JButton rearangeCards = new JButton("Rearange");
        rearangeCards.setLocation(0, 0);
        rearangeCards.setSize(150, 50);
        rearangeCards.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                rearrangeCards();
                textArea.append("\nrearanging cards");
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
        rearangeCards.setVisible(true);
        
        this.add(rearangeCards);
        
        //create second button
        JButton rearangeCards2 = new JButton("Rearange2");
        rearangeCards2.setLocation(200, 0);
        rearangeCards2.setSize(150, 50);        
        rearangeCards2.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                rearrangeCards();
                textArea.append("\nr2");
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
        //logger.info("placing dummy cards");
        placeDummyCards();
        
        //logger.info("placing cards");
        placeCardsOnTable();
        
        //textArea message
        textArea.setText("Click on " + faceCardArray.getLast().getRank() + 
                " or a " + faceCardArray.getFirst().getSuit());
        
        //frame settings
        this.setSize(1000, 800);
        this.setLocation(500, 200);
        this.setTitle("Choices");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        this.setVisible(true);       
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
        ComputerCard computer1 = new ComputerCard();         
        computer1.setLocation(Card.COMPUTER1LOCATION);
        
        //create ComputerCard for computer2        
        ComputerCard computer2 = new ComputerCard();         
        computer2.setLocation(Card.COMPUTER2LOCATION);
        
        //create ComputerCard for computer3   
        ComputerCard computer3 = new ComputerCard();
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
        int aaa = 600/playerHand.size(); 
        
        for(PlayerCard card: playerHand){          
            card.setLocation(playerX, 400);  
            playerX -= aaa;           
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
    }
    
    public void checkForWinner(Collection hand, String winner, String message ){
        if(hand.isEmpty()){
            JOptionPane.showMessageDialog(null, winner, "InfoBox: " + message, JOptionPane.INFORMATION_MESSAGE);
        }        
    }
    
    public void computerAI(ArrayList<PlayerCard> computer1hand,ArrayList<PlayerCard> computer2hand,
            ArrayList<PlayerCard> computer3hand, ArrayDeque<PlayerCard> faceCardArray,
            ArrayDeque<PlayerCard> communityCardsArray, ArrayList<PlayerCard> playerHand){
        
        PlayerCard cardToPlace = faceCardArray.getLast();
        
        checkForWinner(playerHand, "Human Player Wins" ,"CONGRATULATIONS YOU ARE THE WINNER !");
                        
    }
}
