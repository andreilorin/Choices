/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.Card;
import app.Dealer;
import app.TouchCard;
import app.TouchCardBack;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lorin
 */
public class Table extends JFrame{
    
    private Dealer dealer;
    
    private final String[] 
        RANK = {"2", "3", "4", "5", "6", "7", 
                "8", "9", "10", "J", "Q", "K", "A"},
        SUIT = {"club", "diamond", "heart", "spade"};
    
    //place to load all the images
    ArrayList<ImageIcon> allImages = new ArrayList<>(); 
    
    ArrayList<TouchCard> allTheCards;

    
    public static void main(String[] args) {
        Table t = new Table();
        TouchCard tc = t.allTheCards.get(22);
        tc.setIcon(tc.getCardIcon());
        tc.setLocation(400, 300);
        t.add(tc);
        
    }
    
    public Table(){
        //JFrame frame = new JFrame("Test");
        this.setLayout(null);        
        
        //add images to to -allImages- ArrayList
        for(int i=0; i<52; i++){
            
            String name = "C:\\Users\\Lorin\\Documents\\NetBeansProjects\\Choices\\App\\src\\app\\images\\" + i + ".png";
                       
            try{
                ImageIcon bigImage = new ImageIcon(name);                
                allImages.add(bigImage);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        
        //instantiate dealer
        dealer = new Dealer();
        
        //create and add all the cards to the array
        allTheCards = dealer.createAllTheTouchCards(RANK, SUIT, allImages);//   

        //======================================================================
        //TODO - create TouchCard interface/abstract class
        //======================================================================
        
        //create image for deck card        
        TouchCardBack deckCard = new TouchCardBack();
        deckCard.setSize(110, 160);
        deckCard.setBorder(new LineBorder(Color.GREEN, 3));
        deckCard.setLocation(450, 200);
        
        //create image for computer1        
        TouchCardBack computer1 = new TouchCardBack();
        computer1.setSize(110, 160);        
        computer1.setLocation(0, 200);
        
        //create touchcardback
        TouchCardBack computer2 = new TouchCardBack();
        computer2.setSize(110, 160);
        computer2.setLocation(250, 150);
        
        
        this.add(deckCard);
        this.add(computer1);
        this.add(computer2);
        
        this.setSize(1000, 600);
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
}
