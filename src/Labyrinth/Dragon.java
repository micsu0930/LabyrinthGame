package Labyrinth;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class Dragon extends Sprite {
	
	private double velx;
	private double vely;

	public Dragon(int x, int y, int width, int height, Image image) {
		super(x, y, width, height, image);
		// TODO Auto-generated constructor stub
	}
	
	public Rectangle getOffsetBounds() {
	    return new Rectangle((int)(x + velx), (int)(y+vely) , (int)(width+velx), (int)(height+vely));
	}

	
	public void move() {
        if ((velx < 0 && x > 0) || (velx > 0 && x + width <= 800)) {
            x += velx;
        }
		if ((vely < 0 && y > 0) || (vely > 0 && x + height <= 800)) {
            y += vely;
        }
    }
	


    public double getVely() {
		return vely;
	}

	public void setVely(double vely) {
		this.vely = vely;
	}

	public double getVelx() {
        return velx;
    }

    public void setVelx(double velx) {
        this.velx = velx;
    }
    
    public void randomDir() {
    	Random rand = new Random();
    	int dir = rand.nextInt(4);
    	
    	if (dir == 0) {
    		velx = 1;
    		vely = 0;
    	}else if (dir == 1) {
    		velx = -1;
    		vely = 0;
    	}else if (dir == 2) {
    		velx = 0;
    		vely = 1;
    	}else if (dir == 3) {
    		velx = 0;
    		vely = -1;
    	}
    }

}
