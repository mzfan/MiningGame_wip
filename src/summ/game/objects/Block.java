package summ.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import summ.game.framework.GameObject;
import summ.game.framework.ObjectId;

public class Block extends GameObject{
	
	protected boolean breakable; // can player drill through it
	protected int tier; // num from 1 - 3, how easily it can be drilled through, higher tier needs better drill
	protected int pointVal;
	protected BufferedImage sprite;
	
	public Block(int x, int y, ObjectId id) {
		super(x, y,64,64, id);
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(139,69,19));
		g.drawImage(sprite,(int)x,(int)y,(int)(x+width),(int)(y+height),0,0,(int)width,(int)height,null);
		
	}

	@Override
	public Rectangle getBounds() { //hit box
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}
	
	protected void loadSprite(String dir){
		try { 
			this.sprite = ImageIO.read(new File(dir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getTier(){
		return this.tier;
	}
	
	public boolean isBreakable(){
		return this.breakable;
	}
	
	public int getPoints(){
		return this.pointVal;
	}
	

}
