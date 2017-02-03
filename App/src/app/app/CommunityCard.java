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
 * Representation of the CommunityCard, extends Card
 * @author Lorin
 */
public class CommunityCard extends Card {
    
    /**
     * Constructor that gets a handle to the faceCard array and human card array
     * Adds MouseListener
     * @param table
     * @param faceCard
     * @param playerHand
     * @param communityCardsArray 
     */
    public CommunityCard(Table table, ArrayDeque<PlayerCard> faceCard, ArrayList<PlayerCard> playerHand, ArrayDeque<PlayerCard> communityCardsArray) {
                
        //settings
        setLocation(550, 200);
        
        //add mouse listener
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //get the source of the event
                CommunityCard cc = (CommunityCard)e.getSource();
                
                //Get the first card from and communitycardarray and place it
                //in the humanplayer array
                playerHand.add(communityCardsArray.pollFirst());
                
                System.out.println("COMM CARD pressed");
                                
                cc.setBorder(new LineBorder(Color.ORANGE, 3));

                table.placePlayerCardsOnTable();
                
                Dealer.refillCommunityCards(communityCardsArray, faceCard);
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
