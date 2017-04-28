package table;

import cards.PlayerCard;
import cards.FaceCard;
import cards.ComputerCardBack;
import cards.CommunityCard;
import cards.Card;
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
 */
public class Table extends JFrame{
    
    //Dealer
    public Dealer dealer = new Dealer();
    
    //Computer player hands
    public ComputerCardBack computer1;
    public ComputerCardBack computer2;
    public ComputerCardBack computer3;
    
    //assistant
    public Assistant assistant;
    
    //field to display image of the face card
    public FaceCard faceCard;
   
    //Array to load all the images
    public ArrayList<ImageIcon> allImages = new ArrayList<>(); 
    
    //Array to store all the cards
    public ArrayList<PlayerCard> allTheCards;
    
    //ArrayList for computer players 
    public ArrayList<PlayerCard> computer1hand = new ArrayList<>();
    public ArrayList<PlayerCard> computer2hand = new ArrayList<>();
    public ArrayList<PlayerCard> computer3hand = new ArrayList<>();
    
    //ArrayList for human player
    public ArrayList<PlayerCard> playerHand = new ArrayList<>(); 
    
    //ArrayList of players AL
    public ArrayList<ArrayList<PlayerCard>> allPlayers = new ArrayList<>();
            
    //ArrayList for faceCard
    public ArrayDeque<PlayerCard> faceCardArray = new ArrayDeque<>();
        
    //ArrayDeque for ComunityCards
    public ArrayDeque<PlayerCard> communityCardsArray =  new ArrayDeque<>();
    
    //main method    
    public static void main(String[] args) {        
        new Table();
    }
    
    public Table(){   
        addCardImages("src\\resources\\");
        addCardsToArray();
        addPlayersArraysInAllPlayersArray(allPlayers, computer1hand, computer2hand, computer3hand, playerHand);
        dealCards();
        placeRearangeButton();
        placeAssistantButton();
        placeDummyCards();
        placeCardsOnTable();
        setTableFrameAttributes();
        addAssistant();
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
        computer1 = new ComputerCardBack();         
        computer1.setLocation(Card.COMPUTER1LOCATION);
        
        //create ComputerCard for computer2        
        computer2 = new ComputerCardBack();         
        computer2.setLocation(Card.COMPUTER2LOCATION);
        
        //create ComputerCard for computer3   
        computer3 = new ComputerCardBack();
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
    
    public void addCardImages(String path){
         for(int i=0; i<52; i++){
            
            String name = path + i + ".png";           
                       
            try{
                ImageIcon bigImage = new ImageIcon(name);                
                allImages.add(bigImage);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    // the method is leaking "this" in the constructor
    public void setTableFrameAttributes(Table table){
        table.setLayout(null);
        table.setSize(1000, 800);
        table.setLocation(500, 200);
        table.setTitle("Choices");
        table.setResizable(false);
        table.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        table.setVisible(true);    
    }
    
    public void setTableFrameAttributes(){
        this.setLayout(null);
        this.setSize(1000, 800);
        this.setLocation(500, 200);
        this.setTitle("Choices");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        this.setVisible(true);         
    }
    
    public void placeRearangeButton(){
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
    }
        
    public void placeAssistantButton(){
        JButton startAssistantButton = new JButton("Assistant");
        startAssistantButton.setLocation(200, 0);
        startAssistantButton.setSize(150, 50);        
        startAssistantButton.addMouseListener(new MouseListener(){
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
        startAssistantButton.setVisible(true);        
        this.add(startAssistantButton);
    }
    
    public void addPlayersArraysInAllPlayersArray(ArrayList<ArrayList<PlayerCard>> allPlayers, ArrayList<PlayerCard>... arrays){
        for (ArrayList<PlayerCard> array : arrays) {
            allPlayers.add(array);
        }
    }
    
    public void addAssistant(){
        assistant = new Assistant(this);
        assistant.run();
    }
    
    public void addCardsToArray(){
        //create and add all the cards to the array
        allTheCards = dealer.createAllPlayerCards(Card.RANK, Card.SUIT, 
                allImages, this);// 
    }
    
    public void dealCards(){
        dealer.deal(allTheCards, faceCardArray, allPlayers, communityCardsArray);           
    }
    
}
