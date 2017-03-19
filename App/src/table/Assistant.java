package table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Assistant extends JFrame implements Runnable{
    
    public JTextArea textArea;
        
    public Assistant(Table table){
        configureWindowSettings();
        createAndDisplayInfoInAssistant(table);
    }
    
    private void configureWindowSettings(){
        this.setSize(200, 200);
        this.setLocation(100, 100);
        this.setTitle("Choices - Assistant");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        this.setVisible(true);     
    }
    
    private void createAndDisplayInfoInAssistant(Table table){
        JScrollPane jsp = new JScrollPane();
        textArea = new JTextArea();
        
        LocalDateTime timePoint = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDate = timePoint.format(format);
        
        textArea.setText("Hello and Welcome to Choices !");
        textArea.append("\n"+ formattedDate + "| Round 1: click " + table.faceCardArray.getLast().getRank() + 
                " or " + table.faceCardArray.getLast().getSuit());
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textArea.setColumns(20);
        textArea.setRows(5);
        jsp.setViewportView(textArea);

        getContentPane().add(jsp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }
    
    @Override
    public void run() {}
}
