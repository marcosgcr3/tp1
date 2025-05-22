package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole{
	private static final int MAX_FALL = 100;
	private static final String NAME = Messages.PARACHUTER_ROL_NAME;
	private static final String HELP = Messages.PARACHUTER_ROL_HELP;
	private static final String ICON = Messages.LEMMING_PARACHUTE;
	private static final String SYMBOL = Messages.PARACHUTER_ROL_SYMBOL;
	public ParachuterRole() {
		super(NAME,SYMBOL, HELP);
		
	}
	@Override
	public void play(Lemming lemming) {
		if(!lemming.tieneSuperficieSolida(Direction.DOWN)) {
			lemming.goDown();
			
		}else {
			lemming.walk();
			lemming.disableRole();
		}
		
		
	}

	@Override
	public String getIcon(Lemming lemming) {
		
		return ICON;
	}

	@Override
	public int comprobarCaida() {
		
		return MAX_FALL;
	}

	
	
	@Override
	protected LemmingRole createInstance() {
		
		return new ParachuterRole();
	}
	@Override
	public String toString() {
		return NAME;
	}



	
}
