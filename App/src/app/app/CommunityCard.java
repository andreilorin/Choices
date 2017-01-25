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
 * Representation of the CommunityCard
 * @author Lorin
 */
public class CommunityCard extends Card {
    
    /**
     * Constructor that gets a handle to the faceCard array and human card array
     * @param faceCard
     * @param humanPlayerHand 
     */
    public CommunityCard(ArrayDeque<PlayerCard> faceCard, ArrayList<PlayerCard> humanPlayerHand) {
                
        //settings
        setLocation(550, 200);
        
        //add mouse listener
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //get the source of the event
                CommunityCard cc = (CommunityCard)e.getSource();                
                
                System.out.println("COMM CARD pressed");
                                
                cc.setBorder(new LineBorder(Color.ORANGE, 3));                
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
    
        //add mouse motion listener
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
}
