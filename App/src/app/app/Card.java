package app.app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayDeque;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * Base representation of the card
 * @author Lorin 
 */
public abstract class Card extends JButton{
    //Fields
    public volatile int screenX = 0;
    public volatile int screenY = 0;
    public volatile int myX = 0;
    public volatile int myY = 0;
    
    public final ImageIcon backOfTheCard = new ImageIcon("C:\\Users\\Lorin\\Documents\\NetBeansProjects\\Choices\\App\\src\\app\\images\\deckcard.png");

    /**
     * No arguments constructor with default settings
     */
    public Card() {        
        //settings
        setBorder(new LineBorder(Color.GREEN, 3));
        setBackground(Color.WHITE);
        setBounds(0, 0, 110, 160);        
        setSize(110, 160);        
        setOpaque(false);
        setIcon(backOfTheCard);
    }       
}