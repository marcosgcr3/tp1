package tp1.logic.gameobjects;





import tp1.exceptions.ObjectParseException;

import tp1.exceptions.OffBoardException;
import tp1.exceptions.RoleParseException;
import tp1.logic.Direction;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.view.Messages;

public abstract class GameObject implements GameItem{
	protected Position pos;	 
	protected boolean isAlive;
    protected GameWorld game;
	public GameObject() {
		
	} 
	public GameObject(GameWorld game, Position pos) {
        this.game = game;
        this.pos = pos;
        this.isAlive = true;
    }
	public abstract GameObject copy();
		 
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
		
	}
	public boolean mismapos(GameObject obj) {
		return obj.pos.equals(this.pos);
		
	}
	

	
	
	public abstract GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException;
	 
	

	protected abstract boolean matchObjName(String obj);
	
	 
	public boolean isAlive() {
		return isAlive;
	}
	
	
// public boolean isExit()
	public boolean estaEnPosRelativa(GameObject obj, Direction dir) {
		Position posAux = this.pos.update(dir);
		return posAux.equals(obj.pos);
	}
	
	@Override
	public String toString() {
		String linea;
		linea= pos.toString()+" "+this.getName();
		return linea;
	}
	public abstract String getName();
	public void update() {}
	
	 public abstract String getIcon();

	public boolean setRole(LemmingRole role) {return false;}
	
	public boolean interactWith(Lemming lemming) {return false;}
	public boolean interactWith(Wall wall){return false;}
	public boolean interactWith(ExitDoor door){return false;}
	public boolean interactWith(MetalWall metalWall){return false;}
	
}
