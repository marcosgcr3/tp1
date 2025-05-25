package tp1.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tp1.exceptions.FileConfigurationException;
import tp1.exceptions.GameLoadException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.logic.lemmingRoles.ParachuterRole;
import tp1.logic.lemmingRoles.WalkerRole;
import tp1.view.Messages;
 
public class FileGameConfiguration implements GameConfiguration {
    private String fileName;
    private GameWorld game;
    private int ciclos;
    private int lemmingsMuertos;
    private int lemmingsPuerta; 
    private int towin;
    private int numLemmings; 
    private final GameObjectContainer container;
    public static final FileGameConfiguration NONE = new FileGameConfiguration();
 

    
    public FileGameConfiguration(String fileName, GameWorld game) throws FileConfigurationException {
        this.container = new GameObjectContainer();
		try (Scanner scanner = new Scanner(new File(fileName))) {
           
            
            if (scanner.hasNextLine()) {
                String estado = scanner.nextLine();
                try {
                	estado(estado);
                } catch (FileConfigurationException e) {
                    throw new FileConfigurationException(
                        String.format(Messages.INVALID_GAME_STATUS, estado)
                    );
                }
            }
             
            GameObject obj;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                container.add(GameObjectFactory.parse(line, game));
               
            }
        } catch (FileNotFoundException e) {
            throw new FileConfigurationException(Messages.FILE_NOT_FOUND.formatted(fileName));
        } catch (ObjectParseException | OffBoardException e) {
            throw new FileConfigurationException(e.getMessage());
        }
    }
    

    public FileGameConfiguration() {
    	this.ciclos = 0;
		this.numLemmings = 0;
		this.lemmingsMuertos = 0;
		this.lemmingsPuerta = 0;
		this.towin = 0;
		this.container = new GameObjectContainer();
    	
	
	}


	private void estado (String line) throws FileConfigurationException {
	    try (Scanner scanner = new Scanner(line)) {
	       
	    	ciclos = scanner.nextInt();
	    	numLemmings = scanner.nextInt(); 
	    	lemmingsMuertos = scanner.nextInt();
	        lemmingsPuerta = scanner.nextInt();
	        towin = scanner.nextInt();

	    } catch (Exception e) {
	        
	        throw new FileConfigurationException(Messages.INVALID_GAME_STATUS.formatted(line));
	    }
	}
    @Override
    public int getCycle() {
        return this.ciclos;
    }

    @Override
    public int numLemmingsInBoard() {
        return this.numLemmings;
    }

    @Override
    public int numLemmingsDead() {
        return this.lemmingsMuertos;
    }

    @Override
    public int numLemmingsExit() {
        return this.lemmingsPuerta;
    }

    @Override
    public int numLemmingsToWin() {
        return this.towin;
    }

    @Override
    public GameObjectContainer getGameObjects() {
    	
    	 return container.copy();
    }
   

}
