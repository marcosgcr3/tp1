package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;

public abstract class AbstractRole implements LemmingRole {
	private final String name;

	private final String symbol;
	private final String help;

	public AbstractRole(String name, String symbol, String help) {
		this.name = name;

		this.symbol = symbol; 
		this.help = help;
	
	}
	public LemmingRole parse(String rolWord) {
        if (matchRoleName(rolWord.toLowerCase())) {
            return createInstance();
        } else {
            return null;
        }
    }
	
	protected boolean matchRoleName(String rolWord) {
		return rolWord.equals(name.toLowerCase()) ||rolWord.equals(symbol.toLowerCase()) ;
	}
 

    
    protected abstract LemmingRole createInstance();
    public boolean interactWith(Lemming receiver, Lemming lemming) {
    	return false;
    }
	public boolean interactWith(Wall wall, Lemming lemming){
    	return false;
    }
	public boolean interactWith(MetalWall wall, Lemming lemming){
    	return false;
    }
	public boolean receiveInteraction(GameItem other, Lemming lemming) {
		return false;
	}
	public boolean interactWith(ExitDoor puerta, Lemming lemming) {
		boolean escapa = false;
		if(escapa = puerta.mismapos(lemming)) {
			lemming.escapa();			
			
		}
	    return escapa;
		
	}
	public abstract int comprobarCaida();
	public void start(Lemming lemming) {
		
		
	}
	
	public String getHelp() {
		return help;
	}
	public abstract String getIcon(Lemming lemming);
}
