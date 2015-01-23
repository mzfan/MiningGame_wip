package summ.game.window;

import java.util.Arrays;

public class Map {
	
	private char[][] gridmap;
	/* 
	 * character - block mapping
	 * 
	 * empty -> '-'
	 * dirt -> '+'
	 * stone -> 'w'
	 * copper -> 'c'
	 * silver -> 's'
	 * gold -> 'g'
	 * diamond -> 'd'
	 * 
	 * */
	
	public Map(){
		gridmap = new char[100][10];
		for (char[] row : gridmap)
			Arrays.fill(row, '+');
	}
	
	public char[][] getGridMap(){
		return gridmap;
	}
	
	public void generateMap(){ //generate the walls and minerals for the map
		for (int i=0; i<gridmap.length; i++)
			for (int j=0; j<gridmap[0].length; j++){
				
				if (gridmap[i][j]=='+'){
					double chance = Math.random();
					if (chance <=0.05)
						recurseWall(i,j,0);
				}
				
			}
		for (int i=0; i<gridmap.length; i++)
			for (int j=0; j<gridmap[0].length; j++){
				
				if (gridmap[i][j]=='+'){
					if (i<30){ // ZONE 1 : TIER 1 ONLY
						double chance = Math.random();
						if (chance<=0.3)
							gridmap[i][j]='c';
					}else if (i>=30 && i<60){ // ZONE 2: TIER 1 AND 2
						double chance = Math.random();
						if (chance<=0.15)
							gridmap[i][j]='c';
						else if (chance>=0.85)
							gridmap[i][j]='s';
					}else{ // ZONE 3: TIER 2 AND 3
						double chance = Math.random();
						if (chance<=0.15)
							gridmap[i][j]='g';
						else if (chance>=0.85)
							gridmap[i][j]='d';
					}
				}
			}
	}
	
	// this helper method is used to make lines of walls in the map
	private void recurseWall(int row, int col, int calls){ // calls is how many recursive calls deep it is
		
		if (row<0 || row>=gridmap.length || col<0 || col>=gridmap[0].length) return;
		
		if (calls >=3) return;
		if (gridmap[row][col]=='w') return;
		
		gridmap[row][col]='w';
		
		int randDir = (int)(Math.random()*4);
		if (randDir==2)
			recurseWall(row,col-1,calls+1);
		else if (randDir==3)
			recurseWall(row,col+1,calls+1);
		else
			recurseWall(row+1,col,calls+1);
		
	}

}
