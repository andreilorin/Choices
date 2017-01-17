/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import org.mockito.Mockito;

import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;

/**
 *
 * @author Lorin
 */
public class DealerTest {
    
    private Dealer dealer;
    
    private static ArrayList<Card> allTheCards;
    private static ArrayList<Card> faceCard;
    private static ArrayList<ArrayList<Card>> players;
    private static ArrayDeque<Card> deck;
    
    
    private static ArrayList<ImageIcon> allImages;
    
    private String[] RANK = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};;
    private static final String[] SUIT = {"club", "diamond", "heart", "spade"};
    
    
    public DealerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Start ");

        allImages = new ArrayList<>();
        for(int i=0; i<52; i++){
            
            String name = "C:\\Users\\Lorin\\Documents\\NetBeansProjects\\Choices\\App\\src\\app\\images\\" + i + ".png";
                       
            try{
                ImageIcon bigImage = new ImageIcon(name);                
                allImages.add(bigImage);
            }catch(Exception e){
                System.out.println(e);
            }           
        }        
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("End ");
    }
    
    @Before
    public void setUp() {
        dealer = new Dealer();             
    }
    
    @After
    public void tearDown() {
        System.out.println("end of method test");
    }

    /**
     * Test of createAllTheCards method, of class Dealer.
     * Check if 52 cards have been created
     */
    @Test
    public void testCreateAllTheCards_StringArr_StringArr() {
        System.out.println("createAllTheCards() ");        
               
        ArrayList<Card> result = dealer.createAllTheCards(RANK, SUIT);        
        
        assertEquals(52, result.size());        
    }

    /**
     * Test of createAllTheCards method, of class Dealer.
     * Check if 52 cards have been created
     */
    @Test
    public void testCreateAllTheCards_3args() {
        System.out.println("createAllTheCards()");
        
        allTheCards = dealer.createAllTheCards(RANK, SUIT, allImages);
        assertEquals(52, allTheCards.size());        
    }

    /**
     * Test of deal method, of class Dealer.
     */
    @Test
    public void testDeal() {
        System.out.println("deal() ");
        
        //mock dealer class
        Dealer dealerMock = Mockito.mock(Dealer.class);
        
        //declare captors
        ArgumentCaptor<ArrayList> arg = ArgumentCaptor.forClass(ArrayList.class);
        ArgumentCaptor<ArrayList> arg1 = ArgumentCaptor.forClass(ArrayList.class);
        ArgumentCaptor<ArrayList> arg2 = ArgumentCaptor.forClass(ArrayList.class);
        ArgumentCaptor<ArrayDeque> arg3 = ArgumentCaptor.forClass(ArrayDeque.class);
                
        dealerMock.deal(allTheCards, faceCard, players, deck);
        
        //test if the method was called once
        Mockito.verify(dealerMock, Mockito.times(1)).deal(allTheCards, faceCard, players, deck);
        
        //capture arguments
        Mockito.verify(dealerMock).deal(arg.capture(), arg1.capture(), arg2.capture(), arg3.capture());     
        
        //verify the right type of arguments have been captured
        assertEquals(allTheCards, arg.capture());
        assertEquals(faceCard, arg1.capture());
        assertEquals(players, arg2.capture());
        assertEquals(deck, arg3.capture());
    }
}
