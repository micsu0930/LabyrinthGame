/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Labyrinth;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class GameEngine extends JPanel{

    private final int FPS = 240;
    private final int PLAYER_X = 40;
    private final int PLAYER_Y = 760;
    private final int PLAYER_WIDTH = 35;
    private final int PLAYER_HEIGHT = 35;
    private final int PLAYER_MOVEMENT = 2;
    private final int VISIBLE_SIZE = 240;
    


    private Image background;
    private int levelNum = 0;
    private int sesNum ;
    private Level level;
    private Player player;
    private Dragon dragon;
    private Exit exit;
    private Timer newFrameTimer;
    private Maze maze = new Maze(21,21);
    HighScores highScores;
    ArrayList<HighScore> hs;
    

    
    

    public GameEngine() {
        super();
            try  {
        System.out.print("asd");
        highScores = new HighScores(10);
        hs = highScores.getHighScores();
        int max = -1;
        for(HighScore h : hs){
            if(h.getName()>max){
                max = h.getName();
                sesNum = h.getName()+1;
                System.out.print(sesNum);
            }
        }
        
    } catch (SQLException ex) {
    Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
        background = new ImageIcon("data/background.jpg").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
			@Override
            public void actionPerformed(ActionEvent ae) {
                player.setVelx(-PLAYER_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released A"), "stop left");
        this.getActionMap().put("stop left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setVelx(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setVelx(PLAYER_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released D"), "stop right");
        this.getActionMap().put("stop right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setVelx(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setVely(PLAYER_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released S"), "stop down");
        this.getActionMap().put("stop down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setVely(-PLAYER_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released W"), "stop up");
        this.getActionMap().put("stop up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setVely(0);
            }
        });
        newMap();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    public void restart() {
    	levelNum=0;
        sesNum++;
    	newMap();
    }
    
    public void newMap() {
        try {
        	maze.generateMaze();
            level = new Level(maze.getMaze());
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Image playerImage = new ImageIcon("data/player.jpg").getImage();
        player = new Player(40, PLAYER_Y, PLAYER_WIDTH, PLAYER_HEIGHT, playerImage);
        Image exitImage = new ImageIcon("data/exit.png").getImage();
        exit = new Exit(760, 40, 40, 40, exitImage);
        
        dragon = spawnDrag(maze.getMaze());
        dragon.randomDir();
    }
    
    private Dragon spawnDrag(int maze[][]) {
    	int cord=0;
    	do {
    		Random r = new Random();
    		cord = r.nextInt(14-7)+7;
    	} while (maze[cord][cord]==0);
    	
    	Image dragonImage = new ImageIcon("data/dragon.png").getImage();
    	return new Dragon(cord*40, cord*40, 35, 35, dragonImage);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 840, 840, null);
        level.draw(grphcs);
        player.draw(grphcs);
        exit.draw(grphcs);
        dragon.draw(grphcs);
        
        Graphics2D g2d = (Graphics2D)grphcs;
        grphcs.setColor(Color.BLACK);
        Rectangle r = getVisibleBounds();
        Area a = new Area(new Rectangle(0, 0,getWidth(), getHeight()));
        a.subtract(new Area(new Ellipse2D.Double(r.x+20, r.y+20, r.width, r.height)));
        g2d.fill(a);
        
    }
    
    private Rectangle getVisibleBounds() {

        return new Rectangle(player.getX() - VISIBLE_SIZE/ 2,
                player.getY() - VISIBLE_SIZE/ 2, VISIBLE_SIZE, VISIBLE_SIZE);
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
        	
        		
            
                player.move();
                
                dragon.move();
                level.collides(player);
                level.collidesD(dragon);
                if(player.collides(exit)) {
                	levelNum++;
                	newMap();
                }
                if(player.collides(dragon)) {
                    try {
                        highScores.putHighScore(sesNum,levelNum);
                	restart();
                    } catch (SQLException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            
            repaint();
        }

    }
    }
