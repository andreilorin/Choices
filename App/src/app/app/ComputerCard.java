package app.app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 * Representation of the computer card
 * @author Lorin
 */
public class ComputerCard extends Card {
    
    public ComputerCard(){       
        
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
    
    public String getName(){
        return this.toString();
    }
}
