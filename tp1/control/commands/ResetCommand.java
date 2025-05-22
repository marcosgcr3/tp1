package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends Command{
	private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;
    private int nLevel =-1;
    private boolean level = false;
	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		
	}
	
	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		 boolean reset = game.reset(nLevel);
	        if (reset) {
	            view.showGame();
	        }
	        else {
	        	throw new CommandExecuteException(Messages.INVALID_LEVEL_NUMBER);
	        }
		
    	
    	 
    		
	}
	

	@Override
	public Command parse(String[] commandWords)  throws CommandParseException {
		if (commandWords.length>=1 && this.matchCommandName(commandWords[0])) {
			if(commandWords.length==2) {
				this.level = true;
				this.nLevel =Integer.parseInt(commandWords[1]);
			}else {
				this.level = false;
				this.nLevel = -1;
			}
			return new ResetCommand();
			
		}
	    return null;
	}


	

}
