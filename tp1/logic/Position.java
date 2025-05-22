package tp1.logic;

import tp1.view.Messages;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public final class Position {
	private final int row;
	private final int col;
	
	public Position(int x, int y) {
		this.row = x;
		this.col = y;
	}
	
	public Position(Position pos) {
		
		this.col = pos.col;
		this.row = pos.row;
	}
	
	public String toString() {
		return Messages.POSITION.formatted(col, row) ;
	}
	
	public  Position update(Direction move) {
		return new Position(this.row + move.getX(), this.col + move.getY());
 	}
	public boolean estaEnTablero() {
		return (this.col >= 0 && this.col < Game.DIM_X ) && (this.row >= 0 && this.row<Game.DIM_Y);
	}
	@Override
	public boolean equals(Object other) {
		return other instanceof Position && this.col == ((Position)other).col && this.row == ((Position)other).row;
	}
}
