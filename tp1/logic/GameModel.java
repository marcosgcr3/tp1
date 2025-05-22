package tp1.logic;

import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.OffBoardException;
import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {
	public boolean isFinished();
	public void update();
	public boolean reset(int level);
	public void exit();
	public boolean setRole(LemmingRole role, Position pos) throws OffBoardException;
	public void load(String fileName) throws GameLoadException;
	public void save(String fileName) throws GameModelException;

	
}
