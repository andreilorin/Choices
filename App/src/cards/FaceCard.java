package cards;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;

/**
 * Representation of the face card, extends Card
 */
public class FaceCard extends Card {
    
    public FaceCard(ArrayDeque<PlayerCard> faceCardArray) {
        
        this.setIcon(faceCardArray.getFirst().getCardIcon());
        setLocation(350, 200);
        
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { 
                //get the source of the event                
                FaceCard pc = (FaceCard)e.getSource(); 
                System.out.println("FACE CARD clicked");
                
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
