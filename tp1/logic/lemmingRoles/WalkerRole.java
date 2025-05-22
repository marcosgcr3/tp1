 package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class WalkerRole extends AbstractRole{
	private static final int MAX_FALL = 3;

	
	private static final String NAME = Messages.WALKER_ROL_NAME;
	private static final String HELP = Messages.WALKER_ROL_HELP;
	private static final String ICON_RIGHT = Messages.LEMMING_RIGHT;
	private static final String ICON_LEFT = Messages.LEMMING_LEFT;
	
	private static final String SYMBOL = Messages.WALKER_ROL_SYMBOL;

	public WalkerRole() {
		super(NAME,SYMBOL, HELP);
	}
	@Override
	public int comprobarCaida() {
		return MAX_FALL;
	}
	@Override
	public void play(Lemming lemming) {
		if (!lemming.onAir()) {
			lemming.walk();
		}else {
			lemming.fall();
		}
		
	}
	@Override
   public String getIcon(Lemming lemming) {
		return lemming.getDir()== Direction.RIGHT ? ICON_RIGHT : ICON_LEFT;
	}

    @Override
    protected LemmingRole createInstance() {
        return new WalkerRole();
    }
   
	@Override
	public String toString() {
		return NAME;
	}
		
	
		


	
}
