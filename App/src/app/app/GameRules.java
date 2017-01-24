/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.app;

import java.util.ArrayList;

/**
 *
 * @author Lorin
 */
public class GameRules {
    
    public void playerClickHisCard(ArrayList<PlayerCard> faceCard, ArrayList<PlayerCard> humanPlayerHand){
        if(faceCard.get(0).equals(humanPlayerHand.get(0))){
            System.out.println("they are the same");
        }
        else
            System.out.println("not the same");
        
        
    }
    
}
