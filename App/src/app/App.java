package app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Lorin
 */
public class App {

    private static final String[] 
            RANK = {"2", "3", "4", "5", "6", "7", 
                "8", "9", "10", "J", "Q", "K", "A"},
            SUIT = {"club", "diamond", "heart", "spade"};     
    
    public static void main(String[] args) {
        //ArrayList for players hand
        ArrayList<Card> player1 = new ArrayList<>();
        ArrayList<Card> player2 = new ArrayList<>();
        ArrayList<Card> player3 = new ArrayList<>();
        
        //ArrayList for faceCard
        ArrayList<Card> faceCard = new ArrayList<>();
        
        //place to load all the images
        ArrayList<BufferedImage> allImages = new ArrayList<>();
        
        //add images to ArrayList
        for(int i=0; i<52; i++){
            
            String name = "C:\\Users\\Lorin\\Documents\\NetBeansProjects\\Choices\\App\\src\\app\\images\\" + i + ".png";
                       
            try{
            BufferedImage bigImage = ImageIO.read(new File(name));
            allImages.add(bigImage);
            }catch(Exception e){
                System.out.println(e);
            }           
        }        
        //ArrayList of players AL
        ArrayList<ArrayList<Card>> allPlayers = new ArrayList<>();
        
        //add players to allPlayers ArrayList
        allPlayers.add(player1);
        allPlayers.add(player2);
        allPlayers.add(player3);
        
        //create dealer
        Dealer dealer = new Dealer();
        
        //create the cards with images
        ArrayList<Card> allTheCards = dealer.createAllTheCards(RANK, SUIT, allImages);//   
        
        //deal the cards 
        dealer.deal(allTheCards, faceCard, allPlayers);     
        
    }
    
}
