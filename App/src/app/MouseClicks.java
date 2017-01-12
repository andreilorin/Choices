/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Lorin
 */
public class MouseClicks implements MouseListener{

    ArrayList<app.Card> faceCard;// = new ArrayList<>();    
    ArrayList<app.Card> allTheCards;// = new ArrayList<>();
    ArrayList<app.Card> player1;// = new ArrayList<>();
    JButton button;// = new JButton();
    
    int card = 0;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(card);
        faceCard.add(allTheCards.get(0));                  // TODO
        button.setIcon(player1.get(card).getCardIcon());
        card++;// TODO
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
