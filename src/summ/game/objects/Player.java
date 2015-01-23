package summ.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import summ.game.framework.GameObject;
import summ.game.framework.ObjectId;
import summ.game.window.Game;
import summ.game.window.Handler;

public class Player extends GameObject{
	
	private BufferedImage sprite,drill,drillDown,copter;
	
	private float gravity = 0.25f;
	private final float MAX_SPEED = 10;
	private int face;
		
	private Handler handler;
	private Block drillBlock;
	
	private int drillDir;
	
	public Player(int x, int y, Handler handler, ObjectId id) {
		super(x, y,64,64, id);
		face=1;
		this.handler=handler;
		this.drilling=false;
		loadSprite();
	}

	@Override
	public void tick(LinkedList<GameObject> objects) {
		x += dx;
		y += dy;
		
		if (x<0) x=0;
		else if (x>Game.WIDTH-width) x = Game.WIDTH-width;
		if (y<0) y=0;
		
		
		
		if (falling ){
			dy += gravity;
			if (dy > MAX_SPEED)
				dy = MAX_SPEED;
		}
		
		collision(objects);
		
		if (drilling){
			System.out.println(drillDir);
			if (drillDir==2){ // DOWN
				
				float tempH = drillBlock.getHeight();
				
				drillBlock.setY(drillBlock.getY()+2);
				drillBlock.setHeight(tempH-2);
				setX(drillBlock.getX());
				setY(getY()+2);
				if (drillBlock.getHeight()<=0){
					drilling=false;
					handler.markRemoval(drillBlock);
				}
			}else if (drillDir==4){//LEFT
				
			}else if (drillDir==6){//RIGHT
				
			}
		}
	}
	
	private void collision(LinkedList<GameObject> objects){
		for (GameObject ob : objects){
			if (ob.getId()==ObjectId.Block){
				
				
				
				if (getBoundsTop().intersects(ob.getBounds())){
					y = ob.getY() + ob.getHeight();
					dy = 0;
					
				}
				
				if (getBounds().intersects(ob.getBounds())){
					drillBlock=(Block) ob;
					y = ob.getY() - ob.getHeight();
					dy=0;
					falling=false;
					flying=false;
					
					drillDir=2;
					
				}
				
				//RIGHT collision
				if (getBoundsRight().intersects(ob.getBounds())){
					x = ob.getX() - ob.getWidth();
					drillDir = 6;
				}
				//LEFT collision
				if (getBoundsLeft().intersects(ob.getBounds())){
					x = ob.getX() + ob.getWidth();
					drillDir = 4;
					
				}
				
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage(sprite, (int)x, (int)y, null);
		
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)(x+width/2-width/2/2),(int)(y+height/2),(int)width/2,(int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int)(x+width/2-width/2/2),(int)y,(int)width/2,(int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)(x+width-5),(int)y+5,(int)5,(int)height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x,(int)y+5,(int)5,(int)height-10);
	}
	
	
	private void loadSprite(){
		try { 
			this.sprite = ImageIO.read(new File("img/Player1_Right.png"));
			this.drill = ImageIO.read(new File("img/Player1_Right_Drill.png"));
			this.drillDown = ImageIO.read(new File("img/Player1_Down_Drill.png"));
			this.copter = ImageIO.read(new File("img/Player1_Right_Propeller.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void faceLeft(){
		face = -1;
	}
	public void faceRight(){
		face = 1;
	}


	

}
