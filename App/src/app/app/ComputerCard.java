package app.app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.LineBorder;

/**
 * Representation of the computer card, extends Card
 * @author Lorin
 */
public class ComputerCard extends Card {
    
    /**
     * No arguments constructor with default settings
     * Adds MouseMotionListener
     */
    public ComputerCard(){       
        //set border color to differentiate from other type of cards
        setBorder(new LineBorder(Color.RED, 3));
        
        //add mouse listener
        addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                //get the source of the event 
                ComputerCard cc = (ComputerCard)e.getSource();
                
                System.out.println("COMPUTER CARD clicked");                
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
    }
}
