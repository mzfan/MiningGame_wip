package summ.game.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject { // the basis of all the game objects
	
	protected ObjectId id;
	
	protected float x,y;
	protected float dx,dy;
	
	protected float width, height;
	
	protected boolean falling=true;
	protected boolean flying=false;
	protected boolean drilling=false;
	


	public GameObject(int x, int y, float width, float height, ObjectId id){
		this.x=x;
		this.y=y;
		this.id=id;
		this.width=width;
		this.height=height;
		dx=dy=0;
	}
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public abstract void tick(LinkedList<GameObject> objects); //linklisted used for collision detection
	
	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds();
	
	//position
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x=x;
	}
	public void setY(float y){
		this.y=y;
	}
	
	//velocity
	public float getDX(){
		return dx;
	}
	public float getDY(){
		return dy;
	}
	
	public void setDX(float dx){
		this.dx=dx;
	}
	public void setDY(float dy){
		this.dy=dy;
	}
	
	//game object type
	public ObjectId getId(){
		return id;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}
	
	public boolean isDrilling() {
		return drilling;
	}

	public void setDrilling(boolean drilling) {
		this.drilling = drilling;
	}
}
