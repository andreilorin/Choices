package app.app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * Represents player cards, extends Card
 * @author Lorin
 */

public class PlayerCard extends Card {
    //Fields
    private String
            rank,
            suite;
    
    private final int
            value;
    
    private ImageIcon
            icon;    
    
    /**
     * Constructor that gives a handle to faceCard and humanPlayerHand arrays
     * @param rank
     * @param suite
     * @param icon
     * @param faceCard
     * @param humanPlayerHand
     */
    public PlayerCard(String rank, String suite, ImageIcon icon, ArrayDeque<PlayerCard> faceCard, ArrayList<PlayerCard> humanPlayerHand) {
        
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
               
                System.out.println("clicked on player card " + pc.getCard());                        
                      
                pc.setBorder(new LineBorder(Color.BLACK, 3));
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
    
    //Getters
    public String getCard(){return this.rank + " of " + this.suite;}
    
    public String getRankSuit(){return this.rank + " of " + this.suite;}
    
    public int getValue(){return this.value;}
    
    public ImageIcon getCardIcon(){return this.icon;}
}