
package Labyrinth;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Level {

    // each brick is 40x20, so there can be at most 20 bricks side by side
    // the last 10 rows will be empty, so there can be at most 20 rows of bricks
    private final int WALL_WIDTH = 40;
    private final int WALL_HEIGHT = 40;
    ArrayList<Wall> walls;

    public Level(int maze[][]) throws IOException {
        loadLevel(maze);
    }

    public void loadLevel(int maze[][]) {
        
        walls = new ArrayList<>();
        
        for(int x=0;x<maze.length;x++) {
        	for (int y=0;y<maze.length;y++) {
        		if(maze[x][y] == 1) {
        			Image image = new ImageIcon("data/wall.jpg").getImage();
                    walls.add(new Wall(x * WALL_WIDTH, y * WALL_HEIGHT, WALL_WIDTH, WALL_HEIGHT, image));
        		}
        	}
        }
        
    }

    public void collides(Player player) {
    	
    	Rectangle p = player.getOffsetBounds();
    	
        for (Wall wall : walls) {
            Rectangle w = new Rectangle(wall.getX(),wall.getY(),wall.getWidth(),wall.getHeight());
                
            if(p.intersects(w)) {
            	
            	if(player.getVelx() > 0) {
            		player.setVelx(0);
            		player.setX(wall.getX()-WALL_WIDTH);
            	} else if (player.getVelx() < 0) {
            		player.setVelx(0);
            		player.setX(wall.getX()+wall.getWidth());
            	}
            	
            	if(player.getVely()  > 0) {
            		player.setVely(0);
            		player.setY(wall.getY()-WALL_HEIGHT);
            	} else if(player.getVely() < 0) {
            		player.setVely(0);
            		player.setY(wall.getY()+wall.getHeight());
            	}
            	
            	break;
            
            }
        } 
    }
    
    public void collidesD(Dragon dragon) {
    	
    	Rectangle d = dragon.getOffsetBounds();
    	
        for (Wall wall : walls) {
            Rectangle w = new Rectangle(wall.getX(),wall.getY(),wall.getWidth(),wall.getHeight());
                
            if(dragon.collides(wall)) {
            	
            	if(dragon.getVelx() > 0) {
            		dragon.setVelx(0);
            		dragon.setVely(0);
            		dragon.setX(wall.getX()-35);
            	} else if (dragon.getVelx() < 0) {
            		dragon.setVelx(0);
            		dragon.setVely(0);
            		dragon.setX(wall.getX()+wall.getWidth());
            	}
            	
            	if(dragon.getVely()  > 0) {
            		dragon.setVelx(0);
            		dragon.setVely(0);
            		dragon.setY(wall.getY()-35);
            	} else if(dragon.getVely() < 0) {
            		dragon.setVelx(0);
            		dragon.setVely(0);
            		dragon.setY(wall.getY()+wall.getHeight());
            	}
            	
            	dragon.randomDir();
            	break;
            
            }
        } 
        } 
    
    

    public void draw(Graphics g) {
        for (Wall wall : walls) {
            wall.draw(g);
        }
    }

}
