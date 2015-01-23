package summ.game.objects;

import summ.game.framework.ObjectId;

public class StoneBlock extends Block{

	public StoneBlock(int x, int y, ObjectId id) {
		super(x, y, id);
		this.breakable=false;
		this.tier=4;
		//load bg image
		loadSprite("img/Ground_D_Rock.png");
	}

}
