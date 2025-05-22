package tp1.logic;

public interface GameStatus {

	public int getCycle();
	public int numLemmingsInBoard();
	public int numLemmingsDead();
	public int numLemmingsExit();
	public int numLemmingsToWin();
	public GameObjectContainer getGameObjects();
	public String positionToString(int col, int row);

	public boolean playerWins();
	public boolean playerLooses();
	public void setNumLemmingsInBoard(int numLemmingsInBoard);

	public void setNumLemmingsDead(int numLemmingsDead);

	public void setNumLemmingsToWin(int numLemmingsToWin);

	public void setGameObjectContainer(GameObjectContainer gameObjectContainer);
}
