package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;

public interface LemmingRole {


    public void play( Lemming lemming );
    public String getIcon( Lemming lemming );
	public int comprobarCaida();
	public LemmingRole parse(String input);
	public boolean receiveInteraction(GameItem other, Lemming lemming);
	
	 
	 public String getHelp();


	public boolean interactWith(Lemming receiver, Lemming lemming);
	public boolean interactWith(Wall wall, Lemming lemming);
	public boolean interactWith(ExitDoor door, Lemming lemming);
	public String toString();
	public boolean interactWith(MetalWall metalWall, Lemming lemming);
	  public void start( Lemming lemming );
	
}