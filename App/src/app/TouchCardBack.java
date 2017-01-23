package app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class TouchCardBack extends JButton {
    //fields    
    
    private ImageIcon
            icon;
    
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;

    public TouchCardBack() {
        
        ImageIcon deckImage = new ImageIcon("C:\\Users\\Lorin\\Documents\\NetBeansProjects\\Choices\\App\\src\\app\\images\\deckcard.png");
        
        this.setIcon(deckImage);
        
        
        setBorder(new LineBorder(Color.RED, 3));
        setBackground(Color.WHITE);
        setBounds(0, 0, 110, 160);
        setLocation(500, 400);
        setOpaque(false);

        //add mouse listener
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) { 
                //setIcon(card.getCardIcon());
                System.out.println("click");
                setVisible(false);
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
    public ImageIcon getCardIcon(){return this.icon;}
}