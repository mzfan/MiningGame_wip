package summ.game.objects;

import summ.game.framework.ObjectId;

public class CopperBlock extends Block{

	public CopperBlock(int x, int y, ObjectId id) {
		super(x, y, id);
		this.breakable=true;
		this.tier=1;
		//load bg image
		loadSprite("img/Ground_1_Copper.png");
	}

}
