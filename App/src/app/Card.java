package app;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Lorin
 */
public class Card {    
     
    //fields
    private String
            rank,
            suite;
    
    private int
            value;
    
    private ImageIcon
            icon;
    
    /**Constructor
     * @param rank = 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
     * @param suite = club, diamond, heart, spade 
     */
    public Card(String rank, String suite){
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
    }
    
    /**
     * Constructor
     * @param rank = 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
     * @param suite = club, diamond, heart, spade 
     * @param image 
     */
    public Card(String rank, String suite, ImageIcon icon){
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
    }
    
    //Getters
    public String getCard(){return this.rank + " of " + this.suite;}
    
    public String getRankSuit(){return this.rank + " of " + this.suite;}
    
    public int getValue(){return this.value;}
    
    public ImageIcon getCardIcon(){return this.icon;}
    
}
