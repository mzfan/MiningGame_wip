package summ.game.window;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Window {
	
	public Window(int w, int h, String title, Game game){
		
		// Window size initialized.
		game.setPreferredSize(new Dimension(w,h));
		game.setMaximumSize(new Dimension(w,h));
		game.setMinimumSize(new Dimension(w,h));
		
		
		// BASIC FRAME HOUSEKEEPING
		
		JFrame jf = new JFrame(title);
		jf.add(game);
		jf.pack();
		jf.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		// Start the game thread.
		game.start();
	}

}
