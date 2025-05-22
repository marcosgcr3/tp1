package tp1.control;

import tp1.exceptions.CommandException;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private GameModel game;
	private GameView view;

	public Controller(GameModel game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * @throws CommandExecuteException 
	 * 
	 */ 
	public void run()  {
		String[] words = null;

		view.showWelcome();
	
		view.showGame();
		while ( !game.isFinished()) {
				
			words = view.getPrompt();
			
			try {
				Command command = CommandGenerator.parse(words);
				command.execute(game, view);
				
			}catch (CommandException e) {
				
				view.showError(e.getMessage());
				Throwable cause = e.getCause();
				if (cause != null) {
					view.showError(cause.getMessage());
					
		 		}
				
				System.out.println();
			} 
			
			
			 

		}
		view.showEndMessage();
	}


	

}
