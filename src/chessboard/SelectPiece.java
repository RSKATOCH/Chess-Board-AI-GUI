/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author THE_KATOCH!
 */
public class SelectPiece extends JFrame {
    int piecetype;
    static Board frame;
    int nblack =0;
    int nwhite=0;
    ImageIcon bicon = new ImageIcon(getClass().getResource("black.png"));
    ImageIcon wicon = new ImageIcon(getClass().getResource("white.png"));
    int[][] arr;
    int x,y;
    int dimension;
    public SelectPiece(String name,int[][]ar, int dimen,Board fra ,int x1,int y1,int nw,int nb) {
        super(name);
        arr=ar;
        x=x1;
        y=y1;
        dimension=dimen;
        nblack=nb;
        frame =fra;
        nwhite=nw;
        setResizable(false);
    }
    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(new GridLayout(2,1));
       
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(1,1));
        compsToExperiment.setLayout(new GridLayout(1,2));
        JButton black = new JButton();
        JButton white = new JButton();
        black.setIcon(bicon);
        white.setIcon(wicon);
        compsToExperiment.setPreferredSize(new Dimension(75,75));
        compsToExperiment.setPreferredSize(new Dimension(1*75,2*75));
        
        black.addActionListener(new ActionListener()
        {    
            public void actionPerformed(ActionEvent e)
            {
                if(nblack==8)
                {
                    JOptionPane.showMessageDialog(null, "All Black pawns selected.", "Chess Board: Error", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nblack<8)
                {
                    arr[x][y]=2;
                    frame.setVisible(false);
                    frame = new Board("Chess Board",dimension,arr);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //Set up the content pane.
                    frame.addComponentsToPane(frame.getContentPane());
                    //Display the window.
                    frame.pack();
                    frame.setVisible(true);
                    setVisible(false);
                }
            }
        });
        white.addActionListener(new ActionListener()
        {    
            public void actionPerformed(ActionEvent e)
            {
                if(nwhite==8)
                {
                    JOptionPane.showMessageDialog(null, "All White pawns selected.", "Chess Board: Error", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nwhite<8)
                {
                    arr[x][y]=1;
                    frame.setVisible(false);
                    frame = new Board("Chess Board",dimension,arr);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //Set up the content pane.
                    frame.addComponentsToPane(frame.getContentPane());
                    //Display the window.
                    frame.pack();
                    
                    
                    setVisible(false);
                    frame.setVisible(true);
                    
                }    
            }
        });
        compsToExperiment.add(black);
        compsToExperiment.add(white);
       pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH); 
   }
    private static void createAndShowGUI() {
        /*SelectPiece frame = new SelectPiece("PieceSelection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);*/
    
        
        
       
    }
    
}
    

