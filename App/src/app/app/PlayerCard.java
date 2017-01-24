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

public class PlayerCard extends JButton {
    //fields
    private String
            rank,
            suite;
    
    private final int
            value;
    
    private ImageIcon
            icon;
    
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;

    public PlayerCard(String rank, String suite, ImageIcon icon, ArrayDeque<PlayerCard> faceCard, ArrayList<PlayerCard> humanPlayerHand) {
        
        this.rank = rank;
        this.suite = suite;
        
        switch(rank){
            case "2":
                this.value = 1;
                break;
            case "7":
                this.value = 2;
                break;
            case "A":
                this.value = 3;
                break;
            default:
                this.value = 0;
        }
        
        this.icon = icon;
        
        setBorder(new LineBorder(Color.BLUE, 3));
        setBackground(Color.WHITE);
        setBounds(0, 0, 110, 160);
        setLocation(500, 400);
        setOpaque(false);

        //add mouse listener
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //get the source of the event
                JButton b;
                b = (JButton)e.getSource();
                
                //setIcon(card.getCardIcon());
                System.out.println("click");
                //setVisible(false);                
                if(faceCard.getFirst().equals(humanPlayerHand.get(0))){
                    System.out.println("they are the same");
                }
                else
                System.out.println("not the same");            
                
                
                b.setBorder(new LineBorder(Color.BLACK, 3));
                
            }

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
    
    //Getters
    public String getCard(){return this.rank + " of " + this.suite;}
    
    public String getRankSuit(){return this.rank + " of " + this.suite;}
    
    public int getValue(){return this.value;}
    
    public ImageIcon getCardIcon(){return this.icon;}
}