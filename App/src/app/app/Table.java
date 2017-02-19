package app.app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 * Game class. This is where the game runs
 * @author Lorin
 */
public class Table extends JFrame{
    
    //Dealer
    private Dealer dealer;
    
    //Computer player hands
    ComputerCard computer1;
    ComputerCard computer2;
    ComputerCard computer3;
    
    //assistant
    Assistant assistant;
    
    //field to display image of the face card
    FaceCard faceCard;
    
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
        JButton rearangeCards2 = new JButton("Assistant");
        rearangeCards2.setLocation(200, 0);
        rearangeCards2.setSize(150, 50);        
        rearangeCards2.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Assistant a = new Assistant(Table.this);//******************************************why not just table ?
                a.run();
                textArea.append("Created new Assistant");
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
    
    public void checkForWinner(Collection hand, String message, String winner ){
        if(hand.isEmpty()){
            JOptionPane.showMessageDialog(null, winner, message, JOptionPane.INFORMATION_MESSAGE);
        }        
    }   
    
    PlayerCard cardToMoveArround;
    
    //The computerAI method uses Iterator to prevent ConcurrentModificationException
    public void computerAI(ArrayList<PlayerCard> computer1hand, ArrayDeque<PlayerCard> faceCardArray,
            ArrayDeque<PlayerCard> communityCardsArray){
        
        //Computer1 
        Iterator<PlayerCard> iterator1 = computer1hand.iterator();
        
        while(iterator1.hasNext()){
            PlayerCard card1 = iterator1.next();
            if(card1.getRank().equals(faceCardArray.getLast().getRank()) || 
                    card1.getSuit().equals(faceCardArray.getLast().getSuit())){                
                faceCard.setIcon(card1.getCardIcon());
                faceCardArray.add(card1);                
                computer1hand.remove(card1);                
                break;
            }
//            if(!card1.getRank().equals(faceCardArray.getLast().getRank()) && 
//                    !card1.getSuit().equals(faceCardArray.getLast().getSuit())){
//                computer1hand.add(communityCardsArray.pollFirst());
//            } 
        }
        //Computer2
//        Iterator<PlayerCard> iterator2 = computer2hand.iterator();
//        
//        while(iterator2.hasNext()){
//            PlayerCard card = iterator2.next();
//            if(card.getRank().equals(faceCardArray.getLast().getRank()) || 
//                    card.getSuit().equals(faceCardArray.getLast().getSuit())){                
//                faceCard.setIcon(card.getCardIcon());
//                faceCardArray.add(card);                
//                computer2hand.remove(card);                
//                break;
//            }else{
//                computer1hand.add(communityCardsArray.pollFirst());
//            }
//        }
//        //Computer3
//        Iterator<PlayerCard> iterator3 = computer3hand.iterator();
//        
//        while(iterator3.hasNext()){
//            PlayerCard card = iterator3.next();
//            if(card.getRank().equals(faceCardArray.getLast().getRank()) || 
//                    card.getSuit().equals(faceCardArray.getLast().getSuit())){                
//                faceCard.setIcon(card.getCardIcon());
//                faceCardArray.add(card);                
//                computer3hand.remove(card);                
//                break;
//            }else{
//                computer1hand.add(communityCardsArray.pollFirst());
//            }
//        }
        
    }
    
//    public void computerAI(ArrayList<PlayerCard> computer1hand, ArrayDeque<PlayerCard> faceCardArray,
//            ArrayDeque<PlayerCard> communityCardsArray){
//                        
//        for(PlayerCard card : computer1hand){
//            if(card.getRank().equals(faceCardArray.getLast().getRank()) || 
//                    card.getSuit().equals(faceCardArray.getLast().getSuit())){                
//                //faceCard.setIcon(card.getCardIcon());
//                
//                cardToMoveArround = card;
//                
//                break;
//            }else{
//                computer1hand.add(communityCardsArray.pollFirst());
//            }
//        }
//        faceCard.setIcon(cardToMoveArround.getCardIcon());
//        faceCardArray.add(cardToMoveArround);                
//        computer1hand.remove(cardToMoveArround);
//    }


    
//    public void computerAI(ArrayList<PlayerCard> computer1hand, ArrayList<PlayerCard> computer2hand,
//            ArrayList<PlayerCard> computer3hand, ArrayDeque<PlayerCard> faceCardArray, ArrayDeque<PlayerCard> communityCardsArray){
//        
//        for(PlayerCard card : computer1hand)
//            if(card.getRank().equals(faceCardArray.getLast().getRank()) || 
//                    card.getSuit().equals(faceCardArray.getLast().getSuit())){                
//                faceCard.setIcon(card.getCardIcon());
//                faceCardArray.add(card);                
//                computer1hand.remove(card);                
//                break;
//            }else{
//                computer1hand.add(communityCardsArray.pollFirst());
//            }
        
//        for(PlayerCard card : computer2hand)
//            if(card.getRank().equals(faceCardArray.getLast().getRank()) || 
//                    card.getSuit().equals(faceCardArray.getLast().getSuit())){                
//                faceCard.setIcon(card.getCardIcon());
//                faceCardArray.add(card);                
//                computer2hand.remove(card);
//                break;
//            }else{
//                computer2hand.add(communityCardsArray.pollFirst());
//            }
//        
//        for(PlayerCard card : computer3hand)
//            if(card.getRank().equals(faceCardArray.getLast().getRank()) || 
//                    card.getSuit().equals(faceCardArray.getLast().getSuit())){                
//                faceCard.setIcon(card.getCardIcon());
//                faceCardArray.add(card);                
//                computer3hand.remove(card);
//                break;
//            }else{
//                computer3hand.add(communityCardsArray.pollFirst());
//            }
        
}