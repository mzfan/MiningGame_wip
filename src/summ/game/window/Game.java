package summ.game.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import summ.game.framework.KeyInput;
import summ.game.framework.ObjectId;
import summ.game.objects.Player;

public class Game extends Canvas implements Runnable{

	// Thread handling variables.
	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH,HEIGHT;
	
	// the game objects handler
	Handler handler;
	
	Camera cam;
	
	// initialize game variables
	private void init(){
		
		WIDTH=getWidth();
		HEIGHT=getHeight();
		
		handler = new Handler();
		cam = new Camera(0,0);
		handler.createLevel();
		handler.addObject(new Player(128,256,handler,ObjectId.Player));
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	// Call to start the thread.
	public synchronized void start(){
		if (running) // makes sure only 1 thread is active at a time
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		/* Using system clock, and fps rate
		 * we calculate how much time is needed between ticks (frame updates)
		 * we then queue up these ticks using how much time has passed between runs
		 * and then update the game accordingly
		 * 
		 * Because this is a thread, it will run separately to regular code, but running in sync
		 * this is why while loops work inside the run method
		 * 
		 * run() is called only once the moment the thread starts
		 * */
		init();
		this.requestFocus();
		long lastTime = System.nanoTime(); 
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			// update every second the FPS rate and tick counter (reset the frame/update tracker)
			if(System.currentTimeMillis() - timer > 1000){ 
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	// Game frame update handling method
	private void tick(){
		handler.tick(); //update all objects
		cam.tick(handler.objects.getLast());
	}
	
	// Graphics handling method
	private void render(){
		
		// TRIPLE BUFFERING MODE
		BufferStrategy bs = this.getBufferStrategy();
		if (bs==null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D)g;
		
		/* -- BUFFERING HANDLING END --*/
		
		// ----------- CUSTOM DRAWING START -------------
		

		g.setColor(new Color(139,69,19));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(cam.getX(), cam.getY()); //beginning of camera
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY()); //end of camera
		// ----------- CUSTOM DRAWING END ---------------
		
		g.dispose();
		bs.show(); //blit the next buffer onto the screen
	}
	
	public static void main (String[] args){
		new Window(628,628,"Motherload WIPTITLE",new Game());
		// FOR SOME REASON, 628 x 628 is actually 640 x 640....
	}

}
