package app.app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * Base representation of the card, gets extended by all the other card classes
 * @author Lorin 
 */
public abstract class Card extends JButton{
    //Fields
    public volatile int screenX = 0;
    public volatile int screenY = 0;
    public volatile int myX = 0;
    public volatile int myY = 0;
    
    private static final String[] RANK = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private static final String[] SUIT = {"club", "diamond", "heart", "spade"};
    
    public final ImageIcon backOfTheCard = new ImageIcon("C:\\Users\\Lorin\\Documents\\NetBeansProjects\\Choices\\App\\src\\app\\images\\deckcard.png");

    /**
     * No arguments constructor with default settings
     * Adds MouseMotionListener
     */
    public Card() {        
        //settings
        setBorder(new LineBorder(Color.GREEN, 3));
        setBackground(Color.WHITE);
        setBounds(0, 0, 110, 160);        
        setSize(110, 160);        
        setOpaque(false);
        setIcon(backOfTheCard);
        
        //Add mouse motion listener
        //Record the position where the mouse was clicked for 
        //repositioning implemented in the child classes by adding 
        //MouseListener        
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
     * Returns RANK
     * @return String[]
     */
    public static String[] getRANK() {
        return RANK;
    }

    /**
     * Returns SUIT
     * @return String[]
     */
    public static String[] getSUIT() {
        return SUIT;
    }  
}
