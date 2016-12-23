package app;

import java.awt.image.BufferedImage;

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
    
    private BufferedImage
            image;
    
    /**Constructor
     * @param rank = 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
     * @param suite = club, diamond, heart, spade 
     */
    public Card(String rank, String suite, int value, BufferedImage image){
        this.rank = rank;
        this.suite = suite;
        this.value = value;
        this.image = image;
    }
    
    public String getCard(){
        return this.rank + " of " + this.suite;
    }
    
    public BufferedImage getCardImage(){
        return this.image;
    }
    
    @Override
    public String toString(){
        return this.rank + " of " + this.suite;
    }
    
}
