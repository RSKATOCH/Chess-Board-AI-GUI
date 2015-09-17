/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;
import static chessboard.ChessBoard.box;
import static chessboard.ChessBoard.j;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
/**
 *
 * @author THE_KATOCH!
 */
public class Board extends JFrame {

    /**
     * @param args the command line arguments
     */
    static Board frame;
    static int dimension;
    static int history[][][];
    static int turn = 0;
    int nblack=0 ;
    int nwhite=0 ;
    static int nb,nw;
    static int currentturn = 0;
    static int arr[][];
    static int player=0;
    static int x,y;
    static JPanel grid[][];
    static JButton but[][];
    final static int maxGap = 20;
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;
    JButton applyButton = new JButton("Next");
    GridLayout experimentLayout = new GridLayout();
    ImageIcon black = new ImageIcon(getClass().getResource("black.png"));
    ImageIcon white = new ImageIcon(getClass().getResource("white.png"));
    int buttons;
    public Board(String name, int number, int[][] ar) {
        super(name);
        dimension = number;
        frame=this;
        arr= new int[dimension][dimension];
        //history[turn]=new int[dimension][dimension];
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
               arr[i][j]=ar[i][j];
               history[turn][i][j]=ar[i][j];
            }
        }    
        setResizable(false);
    }
    public Board(String name, int number) {
        super(name);
        frame=this;
        dimension = number;
        nwhite=0;
        nblack=0;
        arr= new int[dimension][dimension];
        setResizable(false);
        initializePositions();
        history=new int[1000][dimension][dimension];
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
               history[turn][i][j]=arr[i][j];
            }
        } 
    }
    void initializePositions()
    {
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
                {
                    arr[i][j]=0;
                }    
            }    
        }
        buttons = black.getIconHeight();
        
    }
    public void setPiece(JButton but, int piecetype){
            if(piecetype==1)
            {
                nwhite++;
                but.setIcon(white);
            }
            else if(piecetype==2)
            {
                nblack++;
                but.setIcon(black);
            }
            nw=nwhite;
            nb=nblack;
        }
    public static boolean comparearray(int[][] a, int[][] b,int dimension)
    {
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
                if(a[i][j]!=b[i][j])
                    return false;
            }    
        }    
        return true;
        
    }
    
    public void addComponentsToPane(final Container pane) {

        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(new GridLayout(2,1));
        grid= new JPanel[dimension][dimension];
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2,1));
        compsToExperiment.setLayout(new GridLayout(dimension,dimension));
        
        
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        compsToExperiment.setPreferredSize(new Dimension(75,75));
        compsToExperiment.setPreferredSize(new Dimension(dimension*75,dimension*75));
        
        //Add buttons to experiment with Grid Layout
        but = new JButton[dimension][dimension];
        
        for(int i=0; i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {    
                /*if(arr[i][j]==1)
                {    
                    but[i][j]=new JButton();
                    but[i][j].setIcon(white);
                    
                }
                else if(arr[i][j]==2)
                {     
                    but[i][j]=new JButton("b");
                    but[i][j].setIcon(black);
                }
                else*/
                {
                    but[i][j]=new JButton();
                    setPiece(but[i][j],arr[i][j]);
                }    
                if((i+j)%2==0)
                {
                    but[i][j].setBackground(Color.WHITE);
                    but[i][j].setContentAreaFilled(false);
                    but[i][j].setOpaque(true);
                }
                else
                {
                    but[i][j].setBackground(Color.GRAY);
                    but[i][j].setContentAreaFilled(false);
                    but[i][j].setOpaque(true);
                }    
                 compsToExperiment.add(but[i][j]);
                 
                but[i][j].addActionListener(new ActionListenerImple(i,j));
            }
        };
        
        //Add controls to set up horizontal and vertical gaps
       /* controls.add(new Label("Horizontal gap:"));
        controls.add(new Label("Vertical gap:"));
        controls.add(new Label(" "));
        controls.add(horGapComboBox);
        controls.add(verGapComboBox);*/
        controls.add(applyButton);
        
        //Process the Apply gaps button press
        applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int[][] arr1=UpdateMatrix.updatemat(arr,dimension,player);
                
                if(comparearray(arr1,arr,dimension))
                {
                    JOptionPane.showMessageDialog(null, "No further moves", "Chess Board: Completion", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                turn++;
                currentturn++;
                player=player==0?1:0;
                frame = new Board("Chess Board",Integer.parseInt(j.getText()),arr1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("CHESSBOARD");
                //Set up the content pane.
                frame.addComponentsToPane(frame.getContentPane());
                //Display the window.
                frame.pack();
                frame.setVisible(true);
                setVisible(false);
                }
            }
        });
        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    
   }
    
    private static class ActionListenerImple implements ActionListener {
        ImageIcon black = new ImageIcon(getClass().getResource("black.png"));
        ImageIcon white = new ImageIcon(getClass().getResource("white.png"));
        int x,y;
        public ActionListenerImple(int p,int q) {
            x=p;
            y=q;
        }
        
        public void actionPerformed(ActionEvent e) {
                    if(arr[x][y]==0 && (nb<8 || nw<8))
                    {    
                        //frame.setVisible(false);
                        SelectPiece p = new SelectPiece("Options",arr,dimension,frame,x,y,nw,nb);
                        p.setTitle("CHESSBOARD : CHOOSE PAWN");
                        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        //Set up the content pane.
                        p.addComponentsToPane(p.getContentPane());
                        //Display the window.
                        p.pack();
                        p.setVisible(true);
                        
                    }
                    else if(arr[x][y]!=0)
                    {
                        JOptionPane.showMessageDialog(null, "Cell already contains piece", "Chess Board: Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "8 pieces are selected for both", "Chess Board: Error", JOptionPane.INFORMATION_MESSAGE);
                    }    
        }
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        Board frame = new Board("BoardLayout",8,arr);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
}
    

