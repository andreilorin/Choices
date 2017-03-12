package cards;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.LineBorder;

/**
 * Class used for cards with their back facing the player 
 */
public class ComputerCardBack extends Card {
    
    public ComputerCardBack(){       

        setBorder(new LineBorder(Color.WHITE, 3));
        
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //get the source of the event 
                ComputerCardBack cc = (ComputerCardBack)e.getSource();
                
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
