package tp1.control.commands;



import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameLoadException;

import tp1.exceptions.OffBoardException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class LoadCommand extends Command{
	private static final String NAME = Messages.COMMAND_LOAD_NAME;
    private static final String SHORTCUT = Messages.COMMAND_LOAD_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_LOAD_DETAILS;
    private static final String HELP = Messages.COMMAND_LOAD_HELP;
    
    private String conf;
	public LoadCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		
	} 

	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		try {
			
			game.load(conf);
			view.showGame();
		} catch (GameLoadException e) {
			
	        throw new CommandExecuteException(Messages.COMMAND_LOAD_INVALID.formatted(conf) ,e);
		 
		}
		
		  
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 2) {
				
					conf = commandWords[1];
					return new LoadCommand();
			}else {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
			
			
			
			
		}else {
			return null;
		}
		
	}

}
