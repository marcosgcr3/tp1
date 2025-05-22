package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameLoadException;
import tp1.exceptions.ObjectParseException;

import tp1.exceptions.OffBoardException;


import tp1.logic.FileGameConfiguration;
import tp1.logic.GameObjectContainer;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;

public class GameObjectFactory {
	private static final List<GameObject> AVAILABLE_OBJECTS = Arrays.asList(
			new Lemming(),
			new MetalWall(), 
			new Wall(),
			new ExitDoor()
		);
	
	public static GameObject parse(String input, GameWorld game) throws ObjectParseException, OffBoardException {
       
		for (GameObject g : AVAILABLE_OBJECTS) {
            
                GameObject obj = g.parse(input, game);
                if (obj != null) {
                    return obj;
                }
          
        }
        throw new ObjectParseException(Messages.COMMAND_LOAD_UNKNOWOBJ.formatted(input));
    }
	
	 
} 