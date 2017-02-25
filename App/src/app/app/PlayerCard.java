package app.app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 * Represents player cards, extends Card
 * @author Lorin
 */

public class PlayerCard extends Card {
    //Fields
    private String rank, suite;    
    private final int value;    
    private final ImageIcon icon;

    /**
     * Constructor that gives a handle to faceCard and humanPlayerHand arrays
     * Adds MouseListener
     * I could pass onlyTable to the constructor
     * @param rank
     * @param suite
     * @param icon     
     * @param table
     */
    public PlayerCard(String rank, String suite, ImageIcon icon, Table table) {
        
        this.rank = rank;
        this.suite = suite;
        
        switch(rank){
            case "2":
                this.value = 1;
                break;
            case "7":
                this.value = 2;
                break;
            case "A":
                this.value = 3;
                break;
            default:
                this.value = 0;
        }
        
        this.icon = icon;
        
        //Set border color to differentiate from other type of cards
        setBorder(new LineBorder(Color.BLUE, 3));       
        setLocation(500, 400);        

        //Add mouse listener
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //Get the source of the event                
                PlayerCard pc = (PlayerCard)e.getSource();
                                              
                System.out.println("clicked on player card " + pc.getCardValues());                        
                      
                pc.setBorder(new LineBorder(Color.BLACK, 3));
                                
                if(pc.getSuit().equals(table.faceCardArray.getLast().getSuit()) ||
                       pc.getRank().equals(table.faceCardArray.getLast().getRank())){
                    
                    //add to faceCard array
                    table.faceCard.setIcon(pc.getCardIcon());                    
                    pc.setLocation(Card.FACECARDLOCATION);
                    table.faceCardArray.add(pc);                    
                    System.out.println("card moved");
                    
                    table.playerHand.remove(pc);
                    
                    //check for winner
                    table.dealer.checkForWinner(table.playerHand, "Human Player Wins","CONGRATULATIONS YOU ARE THE WINNER !");
                                           
                    ComputerAI.move(table);
                    
                    LocalDateTime timePoint = LocalDateTime.now();
                    table.assistant.textArea.append("\n" + timePoint.getHour() + ":" + timePoint.getMinute() + ":" + timePoint.getSecond() +
                        "| Round " + table.dealer.roundNumber  + ": click " + table.faceCardArray.getLast().getRank() + 
                        " or " + table.faceCardArray.getLast().getSuit());
                    
                    table.dealer.roundNumber++;
//                  
                }                            
            }
            
            //Record the position where the mouse was clicked for repositioning
            @Override
            public void mousePressed(MouseEvent e) {
                screenX = e.getXOnScreen();
                screenY = e.getYOnScreen();

                myX = getX();
                myY = getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }

        });
    
        //Add mouse motion listener
        addMouseMotionListener(new MouseMotionListener() {
            //Reposition based on the mouse location difference
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;

                setLocation(myX + deltaX, myY + deltaY);
            }
            @Override
            public void mouseMoved(MouseEvent e) { }

        });
    }
    
    /**
     * Returns a String representation of the card's value
     * @return String
     */
    public String getCardValues(){return this.rank + " of " + this.suite;}
    
    /**
     * Return the value of the cards as an int
     * @return int
     */
    public int getValue(){return this.value;}
    
    /**
     * Returns a String representation of the card's rank
     * @return String
     */
    public String getRank(){return this.rank;}
    
    /**
     * Returns a String representation of the card's suite
     * @return String
     */
    public String getSuit(){return this.suite;}
    
    /**
     * Return the card's icon
     * @return ImageIcon
     */
    public ImageIcon getCardIcon(){return this.icon;}
}
