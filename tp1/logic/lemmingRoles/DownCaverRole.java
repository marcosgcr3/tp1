package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole {
	
	


	private boolean hasCaved = false;
	private static final String NAME = Messages.DOWNCAVER_ROL_NAME;
	private static final String HELP = Messages.DOWNCAVER_ROL_HELP;
	private static final String ICON= Messages.LEMMING_DOWN_CAVER;
	private static final String SYMBOL = Messages.DOWNCAVER_ROL_SYMBOL;

	public DownCaverRole() {
		super(NAME,SYMBOL, HELP);
		
	}
	@Override
	public void play(Lemming lemming) {
		if(this.hasCaved) {
			lemming.goDown();
			this.hasCaved = false;
		}else {
			lemming.disableRole();
			if(lemming.onAir()) {
				lemming.goDown();
			}else {
				lemming.walk();
			}
		}
    }

	@Override
	public String getIcon(Lemming lemming) {
		return ICON;
	}
	
	@Override
    protected LemmingRole createInstance() {
        return new DownCaverRole();
    }

	@Override
	public int comprobarCaida() {
		
		return 0;
	}

	@Override
	public boolean interactWith(Wall wall, Lemming lemming) {
		
		if(this.hasCaved =lemming.estaEnPosRelativa(wall, Direction.DOWN)) {
			wall.die();
		}
		
		return this.hasCaved;
	}
	
	@Override
	public String toString() {
		return NAME;
	}


	



	


	

	

	
}
