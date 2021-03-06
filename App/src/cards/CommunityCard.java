package cards;

import computerai.ComputerAI;
import table.Dealer;
import table.Table;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

/**
 * Representation of the CommunityCard, extends Card
 */
public class CommunityCard extends Card {
    
    public CommunityCard(Table table, ArrayDeque<PlayerCard> faceCard, ArrayList<PlayerCard> playerHand, ArrayDeque<PlayerCard> communityCardsArray) {
                
        setLocation(Card.COMMUNITYDCARDLOCATION);
        
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
                
                ComputerAI.move(table);

                table.rearrangeCards();
                
                LocalDateTime timePoint = LocalDateTime.now();
                table.assistant.textArea.append("\n" + timePoint.getHour() + ":" + timePoint.getMinute() + ":" + timePoint.getSecond() +
                    "| Round " + table.dealer.roundNumber  + ": click " + table.faceCardArray.getLast().getRank() + 
                    " or " + table.faceCardArray.getLast().getSuit());
                    
                Dealer.roundNumber++;
                                
                Dealer.refillCommunityCards(communityCardsArray, faceCard);
                                
//                try{
//                    Thread.sleep(1000);
//                }catch(InterruptedException ex){
//                    ex.printStackTrace();
//                }
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
