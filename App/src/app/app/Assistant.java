/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.app;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Assistant extends JFrame implements Runnable{

    public Assistant(Table table){
        this.setSize(200, 200);
        this.setLocation(100, 100);
        this.setTitle("Assistant");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        this.setVisible(true);        
        
        JScrollPane jsp = new JScrollPane();
        JTextArea ta = new JTextArea();

        ta.setText("Hello and Welcome to Choices !");
        ta.append("\nClick on " + table.faceCardArray.getLast().getRank() + 
                " or " + table.faceCardArray.getLast().getSuit());
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ta.setColumns(20);
        ta.setRows(5);
        jsp.setViewportView(ta);

        getContentPane().add(jsp, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, -4, 400, 300));

        pack();
        

    }
    
    @Override
    public void run() {}
    
    public String updateCardNumbers(Table table){
        int firstPlayer = table.computer1hand.size();
        int secondPlayer = table.computer2hand.size();
        int thirdlayer = table.computer3hand.size();
        
        return "\nComputer1:" + firstPlayer + " Computer2:" + secondPlayer + " Computer3:" + thirdlayer;
        
    }
    
}