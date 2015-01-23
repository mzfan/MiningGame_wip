package summ.game.objects;

import summ.game.framework.ObjectId;

public class DiamondBlock extends Block{

	public DiamondBlock(int x, int y, ObjectId id) {
		super(x, y, id);
		this.breakable=true;
		this.tier=3;
		//load bg image
		loadSprite("img/Ground_3_Diamond.png");
	}

}
