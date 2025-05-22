package tp1.logic.gameobjects;



import tp1.exceptions.ObjectParseException;

import tp1.exceptions.OffBoardException;
import tp1.exceptions.RoleParseException;
import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.logic.lemmingRoles.WalkerRole;
import tp1.view.Messages;

public class Lemming extends GameObject{
	
	private LemmingRole role;
	private Direction dir;
	private static final String NAME = Messages.LEMMING_NAME;
	private int fuerzaC;
	private int contCaida;

	public Lemming(Position pos, GameWorld game, LemmingRole role) {
		super(game, pos);
	
		this.role = role;
		//this.role = new WalkerRole();		
		contCaida = 0;
		dir = Direction.RIGHT;
		
	}

	public Lemming(Position position, LemmingRole rol, Direction dir, int caida, GameWorld game) {
		super(game, position);
	
		this.role = rol;
		//this.role = new WalkerRole();		
		contCaida = caida;
		this.dir = dir;
		
	}
	
	public Lemming() {
		
	}


	public void setRelativePos(Direction dir) {
		pos = pos.update( dir);
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	public Direction getDir() {
		return dir;
	}
	public boolean setRole(LemmingRole role) {
		boolean set = false;
		if(this.role.toString() != role.toString()) {
			this.role = role;
			set = true;
		
		}
	
		return set;
		
		
	}
	public void disableRole() {
		this.role = new WalkerRole();
	}

	public void noCae() {
		
	  
	        // Cambiar dirección si se encuentra un borde
	        if (!pos.update(dir).estaEnTablero() || tieneSuperficieSolida(dir)){
	            setDir(dir.opposite());
	         
	        
	           
	        } else {
	            // Si no se cambia la dirección, el lemming avanza
	        	setRelativePos(dir);
	           
	        }
	    
	}
	public boolean tieneSuperficieSolida(Direction dir) {
		
        return game.isInAir(pos.update(dir));
	}

	
	public boolean onAir() {
		
		return !tieneSuperficieSolida(Direction.DOWN);
	}
	public void fall() {
		if (!pos.update(Direction.DOWN).estaEnTablero()) {
	        die();
	      
	    }else {
	    	contCaida++;
	    	goDown();
	    }
		
	}
	
	public void walk() {
		
		 if (contCaida >= role.comprobarCaida()) {
	            die();
	           
	        }else {
	        	contCaida = 0;
	        	 noCae();
	        }
		
	   
	}
	
	
	public void escapa() {
		
		isAlive = false;
		game.incremetaLemmingsPuerta();
	}
	
	private void die() {
		
		isAlive = false;
		game.incrementarMuertos();
	}
	
	
	
	
	
	public void goDown() {
		setRelativePos(Direction.DOWN);
	}
	
	
	
	public void update() {
		
			role.play(this);
		
	}
	
		
	public void muerto() {
		game.incrementarMuertos();
	}


	
	   
	
	
	@Override
	public boolean isSolid() {
        return false;
    }
	@Override
	public boolean receiveInteraction(GameItem other) {
		return other.interactWith(this);
	}
	
	public boolean interactWith(Wall wall) {
		//if(wall.estaEnPos(this.pos.posDebajo())) {
			 return this.role.interactWith(wall, this);
		//}
	//this.disableRole();
   // return false;
	
	
		
		
    }

    
	@Override
	public boolean interactWith(ExitDoor door) {
		boolean interact = false;
		if(interact =door.isInPosition(this.pos)) {
			this.escapa();			
			
		}
	    return interact;
			
	}
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String toString() {
		return Messages.LEMMING_FORMAT.formatted(pos.toString(), Messages.LEMMING_NAME, dir.toString(), contCaida, role.toString());
		
	}
	@Override
	public String getIcon() {
		
		return this.role.getIcon(this);
	}


	@Override
	protected boolean matchObjName(String obj) {
		return obj.equals(Messages.LEMMING_NAME.toLowerCase()) || obj.equals(Messages.LEMMING_SHORT.toLowerCase());
	}

	@Override
	public GameObject copy() {
		
		return new Lemming(pos, role, dir, fuerzaC,  game);
	}
	@Override
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {

	
	    String[] words = line.trim().split("\\s+");
	

	    if (words[1].equalsIgnoreCase(NAME)) {
	    	
            String[] wPos = words[0].replace("(", "").replace(")", "").split(",");
            
            int row, col;
            try {
                
                row = Integer.valueOf(wPos[0]);
                col = Integer.valueOf(wPos[1]);
            } catch (NumberFormatException e) { 
             
                throw new OffBoardException(Messages.COMMAND_LOAD_OBJPOSINVALID.formatted(line));
            }
            Position newPos = new Position(col, row);

        
            if (!newPos.estaEnTablero()) {
                throw new OffBoardException(Messages.COMMAND_LOAD_POSITIONINVALID.formatted(line));
            }

       
            Direction dir = Direction.asignar(words[2].toUpperCase());
            if (dir == null) {
                throw new ObjectParseException(Messages.UNKNOWN_GAME_OBJECT_DIRECTION.formatted(line));
            }else if (!dir.dirLemmingCorrect()){
            	throw new ObjectParseException(Messages.COMMAND_LOAD_DIRECTIONINVALID.formatted(line));
            }

            int fuerzaC; 
            try {
            	fuerzaC = Integer.valueOf(words[3]);
            } catch (NumberFormatException e) {
                throw new ObjectParseException(Messages.INVALID_HEIGHT.formatted(line));
            }

            LemmingRole role;
            try {
                role = LemmingRoleFactory.parse(words[4]);
            } catch (RoleParseException e) {
                throw new ObjectParseException(Messages.COMMAND_LOAD_ROLEINVALID.formatted(line));
            }
            
	       return new Lemming(newPos, role,dir, fuerzaC, game);
	    }
	    return null;
	}



	
}
