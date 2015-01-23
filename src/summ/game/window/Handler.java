package summ.game.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import summ.game.framework.GameObject;
import summ.game.framework.ObjectId;
import summ.game.objects.CopperBlock;
import summ.game.objects.DiamondBlock;
import summ.game.objects.DirtBlock;
import summ.game.objects.GoldBlock;
import summ.game.objects.SilverBlock;
import summ.game.objects.StoneBlock;
import summ.game.objects.TopDirtBlock;

public class Handler { // this class will handle all the game objects (drawing and updating)
	
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	private GameObject tempObj, removeObj;
	
	private Map map;

	private BufferedImage bg,shops;
	
	private boolean removeBlock=false;
	
	private int verticalOffset = 384; //the ground for the map actually starts 384px down.
	
	public Handler(){
		try { 
			bg = ImageIO.read(new File("img/Bkgd_NightSky_2.png"));
			shops = ImageIO.read(new File("img/Shops_All.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick(){
		for (GameObject obj : objects){
			tempObj = obj;
			tempObj.tick(objects);
		}
		if (removeBlock){
			removeObject(removeObj);
			removeBlock=false;
		}
		
	}
	public void render(Graphics g){
		g.drawImage(bg, 0,-220, null);
		g.drawImage(shops,50,140,null);
		g.fillRect(0, 320, Game.WIDTH, Game.HEIGHT);
		for (GameObject obj : objects){
			tempObj = obj;
			tempObj.render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.objects.add(object);
	}
	public void removeObject(GameObject object){
		this.objects.remove(object);
	}
	public void markRemoval(GameObject object){
		removeBlock=true;
		removeObj = object;
	}
	
	public void createLevel(){
		map = new Map();
		map.generateMap();
		char[][] gridmap = map.getGridMap();
		
		// top layer hardcoded cuz its different.
		for (int i=0;i<10;i++)
			addObject(new TopDirtBlock(i*64,320,ObjectId.Block));
		
		for (int row=0; row<gridmap.length; row++)
			for (int col=0; col<gridmap[0].length; col++){
				if (gridmap[row][col]=='w')
					addObject(new StoneBlock(col*64,row*64+verticalOffset,ObjectId.Block));
				else if (gridmap[row][col]=='+')
					addObject(new DirtBlock(col*64,row*64+verticalOffset,ObjectId.Block));
				else if (gridmap[row][col]=='c')
					addObject(new CopperBlock(col*64,row*64+verticalOffset,ObjectId.Block));
				else if (gridmap[row][col]=='s')
					addObject(new SilverBlock(col*64,row*64+verticalOffset,ObjectId.Block));
				else if (gridmap[row][col]=='g')
					addObject(new GoldBlock(col*64,row*64+verticalOffset,ObjectId.Block));
				else if (gridmap[row][col]=='d')
					addObject(new DiamondBlock(col*64,row*64+verticalOffset,ObjectId.Block));
			}
	}
}
