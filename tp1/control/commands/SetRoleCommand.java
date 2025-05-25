package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.RoleParseException;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.AbstractRole;
import tp1.logic.lemmingRoles.DownCaverRole;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.logic.lemmingRoles.ParachuterRole;
import tp1.logic.lemmingRoles.WalkerRole;
import tp1.view.GameView;
import tp1.view.Messages;

public class SetRoleCommand extends Command{
	private static final String NAME = Messages.COMMAND_SETROLE_NAME;
	private static final String SHORTCUT = Messages.COMMAND_SETROLE_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_SETROLE_DETAILS;
	private static final String HELP = Messages.COMMAND_SETROLE_HELP + LemmingRoleFactory.getHelpRole();

	private LemmingRole role;
	private String letra;
	private int num;
	private	Position pos ;
	public SetRoleCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	
	

	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		
		pos = new Position(  num -1,Integer.parseInt(view.rowNameToNum(letra)));
		
		try {
		
			if(!game.setRole(role, pos)) {
				
				throw new CommandExecuteException(Messages.SETROLE_ERROR_LEMMING.formatted(pos.toString(), role.toString()));
				
			}else {
				game.update();
				view.showGame();
			} 
			
		}catch(OffBoardException e) {
			throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, e);
		}

		
		
		
		
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if (commandWords.length>1 && this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 4) {
				
			
				try {
					try {
						role =LemmingRoleFactory.parse(commandWords[1]);
					} catch (RoleParseException e) {
						throw new CommandParseException(Messages.UNKNOWN_ROLE.formatted(commandWords[1]));
					}
					
					num = Integer.parseInt(commandWords[3]);
					letra = commandWords[2];
				
					
					
						return new SetRoleCommand();
				
					
				
				} catch (NumberFormatException e) {
				 	throw new CommandParseException(Messages.INVALID_POSITION.formatted(Messages.POSITION.formatted( letra, num)));
				 }
				catch (CommandParseException e) {
				 	throw new CommandParseException(Messages.SETROLINVALID_COMMAND, e);
				 }
				
			
			
			
			}else {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
			
		}else {
			return null;
		}
	    
	

	}	
	
}
