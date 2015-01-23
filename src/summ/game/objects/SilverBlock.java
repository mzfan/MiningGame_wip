package summ.game.objects;

import summ.game.framework.ObjectId;

public class SilverBlock extends Block{

	public SilverBlock(int x, int y, ObjectId id) {
		super(x, y, id);
		this.breakable=true;
		this.tier=2;
		//load bg image
		loadSprite("img/Ground_2_Silver.png");
	}

}
