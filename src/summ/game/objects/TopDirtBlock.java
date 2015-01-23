package summ.game.objects;

import summ.game.framework.ObjectId;

public class TopDirtBlock extends Block{

	public TopDirtBlock(int x, int y, ObjectId id) {
		super(x, y, id);
		this.breakable=true;
		this.tier=1;
		//load bg image
		loadSprite("img/Ground_0_TopLayer.png");
	}

}
