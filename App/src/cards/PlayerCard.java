package cards;

import computerai.ComputerAI;
import table.Table;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.time.LocalDateTime;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class PlayerCard extends Card {

    private final String rank, suite;    
    private final int value;    
    private final ImageIcon icon;

    public PlayerCard(String rank, String suite, ImageIcon icon, Table table) {
        
        this.rank = rank;
        this.suite = suite;        
        this.value = setValue(rank);        
        this.icon = icon;
        
        setBorder(new LineBorder(Color.BLUE, 3));       
        setLocation(500, 400);        

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //Get the source of the event                
                PlayerCard pc = (PlayerCard)e.getSource();
                                              
                System.out.println("clicked on player card " + pc.getCardValues());                        
                      
                pc.setBorder(new LineBorder(Color.BLACK, 3));
                
                onclickRulesForPlayerCard(pc, table);                          
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
    
    public String getCardValues(){return this.rank + " of " + this.suite;}
    
    public int getValue(){return this.value;}
    
    public String getRank(){return this.rank;}
    
    public String getSuit(){return this.suite;}
    
    public ImageIcon getCardIcon(){return this.icon;}
    
    private int setValue(String rank){
        switch(rank){
            case "2":
                return 1;
            case "7":
                return 2;
            case "A":
                return 3;
            default:
                return 0;
        }
    }
    
    private void onclickRulesForPlayerCard(PlayerCard pc, Table table){
        
        if(pc.getSuit().equals(table.faceCardArray.getLast().getSuit()) ||
                       pc.getRank().equals(table.faceCardArray.getLast().getRank())){
                    
                    //add to faceCard array
                    table.faceCard.setIcon(pc.getCardIcon());                    
                    pc.setLocation(Card.FACECARDLOCATION);
                    table.faceCardArray.add(pc);                    
                    System.out.println("card moved");
                    
                    table.playerHand.remove(pc);
                    
                    //check for winner
                    table.dealer.checkForWinner(table.playerHand, "Human Player Wins","CONGRATULATIONS YOU ARE THE WINNER !", table);
                                           
                    ComputerAI.move(table);
                    
                    LocalDateTime timePoint = LocalDateTime.now();
                    table.assistant.textArea.append("\n" + timePoint.getHour() + ":" + timePoint.getMinute() + ":" + timePoint.getSecond() +
                        "| Round " + table.dealer.roundNumber  + ": click " + table.faceCardArray.getLast().getRank() + 
                        " or " + table.faceCardArray.getLast().getSuit());
                    
                    table.dealer.roundNumber++;                  
                }                      
    }
}
