package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class HelpCommand extends NoParamsCommand {

    private static final String NAME = Messages.COMMAND_HELP_NAME;
    private static final String SHORTCUT = Messages.COMMAND_HELP_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_HELP_DETAILS;
    private static final String HELP = Messages.COMMAND_HELP_HELP;

    public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}
	
	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		view.showMessage(Messages.HELP_AVAILABLE_COMMANDS);
		view.showMessage(CommandGenerator.commandHelp());
	
	}
	

} 
