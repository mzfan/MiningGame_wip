package summ.game.window;

import summ.game.framework.GameObject;

public class Camera {
	
	private float x,y;
	
	public Camera(float x, float y){
		this.x=x;
		this.y=y;
	}
	
	public void tick(GameObject player){
		this.y += (-player.getY() + Game.HEIGHT/2 - this.y) *0.1;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	

}
