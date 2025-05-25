package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;

public class MetalWall extends GameObject{
	private static final String ICON = Messages.METALWALL;
	private static final String NAME = Messages.METALWALL_NAME;
	public MetalWall(Position pos, GameWorld game) {
		super(game, pos);
		
	}
	
	public MetalWall() {
		
	} 

	@Override
	public String getIcon() {
		return ICON;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	

	@Override
	public boolean receiveInteraction(GameItem other) {
		
		return other.interactWith(this);
	}


	@Override
	public boolean isSolid() {
		
		return true;
	}

	@Override 
	protected boolean matchObjName(String obj) {
		
		return obj.equals(Messages.METALWALL_NAME.toLowerCase()) || obj.equals(Messages.METALWALL_SHORT.toLowerCase());
	}

	
 

	@Override
	public GameObject copy() {
		return new MetalWall(pos, game);
	}


	@Override
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		String[] words = line.trim().split("\\s+");
		if (words[1].equalsIgnoreCase(NAME)) { 
	        try {
	        
	            String[] wPos = words[0].replace("(", "").replace(")", "").split(",");
	            int row, col;
		            try {
		               
		                row = Integer.valueOf(wPos[0]);
		                col = Integer.valueOf(wPos[1]);
		            } catch (NumberFormatException e) {
		                
		            	 throw new OffBoardException(Messages.INVALID_POSITION.formatted(line));
		            }
		            Position newPos = new Position(col, row);
	
		            
		            if (!newPos.estaEnTablero()) {
		                throw new OffBoardException(Messages.INVALID_POSITION.formatted(words[0]));
		            }
	
		            return new MetalWall(newPos, game);
		        } catch (NumberFormatException e) {
		        	throw new ObjectParseException(Messages.INVALID_POSITION.formatted(line));
		    }

		    
			
		}
		return null;
	}

	
}
