/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Labyrinth;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.util.ArrayList;


public class LabyrinthGUI {
    private JFrame frame;
    private GameEngine gameArea;

    public LabyrinthGUI() {
        frame = new JFrame("Labyrinth");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
           
            HighScores highScores = new HighScores(10);
            System.out.println(highScores.getHighScores());
            ArrayList<HighScore> hs = highScores.getHighScores();
            
            JMenuBar menuBar = new JMenuBar();
            frame.setJMenuBar(menuBar);
            JMenu gameMenu = new JMenu("menu");
            menuBar.add(gameMenu);
            JMenuItem resMenu = new JMenuItem("Restart");
            resMenu.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameArea.restart();
                    gameArea = new GameEngine();
                    
                }
            });
            gameMenu.add(resMenu);
            JMenu menuHS = new JMenu("Highscores");
            gameMenu.add(menuHS);
            
            for (HighScore h : hs) {
            JMenuItem hsMenuItem = new JMenuItem("session: " + h.getName() + " score:" + h.getScore());
            menuHS.add(hsMenuItem);
            
        }
            
            System.out.println(highScores.getHighScores());
        } catch (SQLException ex) {
            Logger.getLogger(LabyrinthGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        
        gameArea = new GameEngine();
        frame.getContentPane().add(gameArea);
        
        frame.setPreferredSize(new Dimension(850, 850));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
    
}

