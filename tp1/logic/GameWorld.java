package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Wall;
import tp1.logic.lemmingRoles.LemmingRole;

public interface GameWorld {


	public boolean isInAir(Position pos);
	
	public void incrementarMuertos();
	public void incremetaLemmingsPuerta();
	
	//public void GameConfigurator(String[] words);
	
	
}
