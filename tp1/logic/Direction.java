package tp1.logic;

/**
 * Represents the allowed directions in the game
 *
 */
public enum Direction {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), NONE(0,0);
	
	private int x;
	private int y;
	
	private Direction(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}
 
	public int getY() {
		return y;
	}

	public Direction opposite()
	{		
		return this == RIGHT ? LEFT : RIGHT;
	}
	public boolean dirLemmingCorrect() {
		 return this.equals(RIGHT) || this.equals(LEFT);
		        
		  
				 
	}
	public static Direction asignar(String a) {
		if(a.equals("RIGHT")) {
			return RIGHT;
		}else if(a.equals("LEFT")) {
			return LEFT;
		}else if(a.equals("DOWN")) {
			return DOWN;
		}else if(a.equals("UP")){
			return UP;
		}else {
			return null;
		}
		
	}

	
	
}
