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
public class ChessBoard extends JFrame {

    public ChessBoard(String name) {
        super(name);
        setResizable(false);
    }
    public static ChessBoard box ;
    public static JTextField j;
    private static void createAndShowGUI() {
        //Create and set up the window.
        box = new ChessBoard("Chess Board Demo");
        /*JButton button = new JButton*/
        JButton nextButton = new JButton("NEXT");
        JLabel label = new JLabel("Enter number of rows and columns (n): ");
        j = new JTextField("");
        j.setToolTipText("Enter values");
        j.setText("");
        j.setPreferredSize(new Dimension(50,20));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(label);
        panel1.add(j);
        panel.add(panel1);
        panel.add(nextButton);
        box.add(panel);
        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 100;
        box.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        box.setTitle("A frame with two components");
        box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        box.setVisible(true);
        nextButton.addActionListener(new ActionListenerImpl());
        
    }
    private static class ActionListenerImpl implements ActionListener {

        public ActionListenerImpl() {
        }

        public void actionPerformed(ActionEvent e) {
                    if(j.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Empty input", "Chess Board: Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        if(!j.getText().matches("[(+?)(-?)]?[0-9]+"))
                        {
                            JOptionPane.showMessageDialog(null, "Invalid Input. Only Integers.", "Chess Board: Error", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(Integer.parseInt(j.getText())<2)
                        {
                            JOptionPane.showMessageDialog(null, "Integer should be greater than 1", "Chess Board: Error", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            Board frame = new Board("Chess Board",Integer.parseInt(j.getText()));
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            //Set up the content pane.
                            frame.addComponentsToPane(frame.getContentPane());
                            //Display the window.
                            frame.pack();
                            frame.setVisible(true);
                            box.setVisible(false);
                        }
                    }
        }
    }
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
    

