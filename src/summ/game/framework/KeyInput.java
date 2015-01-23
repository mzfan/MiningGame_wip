package summ.game.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import summ.game.window.Handler;

public class KeyInput extends KeyAdapter{
	
	Handler handler;
	
	public KeyInput(Handler handler){
		this.handler=handler;
	}
	
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		GameObject player = handler.objects.getLast();
		if (player.getId()==ObjectId.Player && !player.drilling){ //double check if the last object is the player or not
			
			if (key == KeyEvent.VK_RIGHT) player.setDX(2);
			if (key == KeyEvent.VK_LEFT) player.setDX(-2);
			if (key == KeyEvent.VK_UP){
				player.setDY(-4);
				player.setFlying(true);
				player.setFalling(false);
			}
			if (key == KeyEvent.VK_DOWN){
				if (!player.isFalling() && !player.isFlying()){
					player.setDY(4);
					player.setDrilling(true);
					
				}
			}
			
		}
		
		
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		GameObject player = handler.objects.getLast();
		if (player.getId()==ObjectId.Player){ //double check if the last object is the player or not
			
			if (key == KeyEvent.VK_RIGHT) player.setDX(0);
			if (key == KeyEvent.VK_LEFT) player.setDX(0);
			if (key == KeyEvent.VK_UP){
				player.setDY(0);
				player.setFlying(false);
				player.setFalling(true);
			}
			
		}
	}

}
