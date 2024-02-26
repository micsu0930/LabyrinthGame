package Labyrinth;

import java.awt.Image;
import java.awt.Rectangle;

public class Player extends Sprite {
	
	private double velx;
	private double vely;

	public Player(int x, int y, int width, int height, Image image) {
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

}
