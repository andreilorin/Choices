/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Lorin
 */
public class Main extends javax.swing.JFrame {
    
    private final String[] 
            RANK = {"2", "3", "4", "5", "6", "7", 
                "8", "9", "10", "J", "Q", "K", "A"},
            SUIT = {"club", "diamond", "heart", "spade"};
    
    //ArrayList for computer players 
    ArrayList<Card> player1 = new ArrayList<>();
    ArrayList<Card> player2 = new ArrayList<>();
    ArrayList<Card> player3 = new ArrayList<>(); 
    
    //ArrayList of players AL
    ArrayList<ArrayList<Card>> allPlayers = new ArrayList<>();
            
    //ArrayList for faceCard
    ArrayList<Card> faceCard = new ArrayList<>();
        
    //ArrayDeque for deck
    ArrayDeque<Card> deck = new ArrayDeque<>();
        
    //place to load all the images
    ArrayList<ImageIcon> allImages = new ArrayList<>();    

    /**
     * Creates new form NewJFrame
     */
    public Main() {
        
        //create deck image
        ImageIcon deckImage = new ImageIcon("C:\\Users\\Lorin\\Documents\\NetBeansProjects\\Choices\\App\\src\\app\\images\\deckcard.png");
        
        //add images to ArrayList
        for(int i=0; i<52; i++){
            
            String name = "C:\\Users\\Lorin\\Documents\\NetBeansProjects\\Choices\\App\\src\\app\\images\\" + i + ".png";
                       
            try{
                ImageIcon bigImage = new ImageIcon(name);                
                allImages.add(bigImage);
            }catch(Exception e){
                System.out.println(e);
            }           
        }        
        
        //add players to allPlayers ArrayList
        allPlayers.add(player1);
        allPlayers.add(player2);
        allPlayers.add(player3);
        
        //create dealer
        Dealer dealer = new Dealer();
        
        //create the cards with images
        ArrayList<Card> allTheCards = dealer.createAllTheCards(RANK, SUIT, allImages);//   
        
        //deal the cards 
        dealer.deal(allTheCards, faceCard, allPlayers, deck);  
        
        
        initComponents();
        
        //Display players cards as buttons with images
        JButton card1 = new JButton();
        card1.setSize(110, 160);
        card1.setLocation(10, 300);
        card1.setIcon(player1.get(0).getCardIcon());  
        
        JButton card2 = new JButton();
        card2.setSize(110, 160);
        card2.setLocation(140, 300);
        card2.setIcon(player1.get(1).getCardIcon());
        
        JButton card3 = new JButton();
        card3.setSize(110, 160);
        card3.setLocation(270, 300);
        card3.setIcon(player1.get(2).getCardIcon());       
        
        JButton card4 = new JButton();
        card4.setSize(110, 160);
        card4.setLocation(400, 300);
        card4.setIcon(player1.get(3).getCardIcon());       
        
        JButton card5 = new JButton();
        card5.setSize(110, 160);
        card5.setLocation(530, 300);
        card5.setIcon(player1.get(4).getCardIcon()); 
        
        //Display faceCard and deckCard as labels with images
        JLabel showFaceCard = new JLabel();
        showFaceCard.setSize(110, 160);
        showFaceCard.setIcon(faceCard.get(0).getCardIcon());
        showFaceCard.setLocation(200, 0);        
       
        JLabel showDeckCard = new JLabel();
        showDeckCard.setSize(110, 160);
        showDeckCard.setIcon(deckImage);
        showDeckCard.setLocation(400, 0);  
        
        ///Add componets to frame
        this.add(showDeckCard);
        this.add(showFaceCard);
        
        this.add(card1);
        this.add(card2);
        this.add(card3);
        this.add(card4);
        this.add(card5);
                
        this.setLocation(600, 300);  
        
        //Inner class for handling mouse clicks
        class MouseClick implements MouseListener{

            int card = 0;
            
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(card);
                
                Card c = player1.get(0);
                
                //TODO----------------------------------------------------------
                //faceCard.add(allTheCards.get(0));  // TODO--------------------              
                faceCard.add(c);
                player1.remove(c);
                showDeckCard.setIcon(c.getCardIcon());
                card1.setIcon(player1.get(card).getCardIcon());
                System.out.println(card1.getIcon().toString());
                card++;//
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
        
        //Add mouse listener to cards
        card1.addMouseListener(new MouseClick());
        card2.addMouseListener(new MouseClick());
        card3.addMouseListener(new MouseClick());
        card4.addMouseListener(new MouseClick());
        card5.addMouseListener(new MouseClick());
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Choices");
        setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {       
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }    
}
