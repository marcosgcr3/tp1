package tp1.logic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.OffBoardException;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.ParachuterRole;
import tp1.logic.lemmingRoles.WalkerRole;
import tp1.view.Messages;

public class Game implements GameModel, GameStatus, GameWorld {

    public static final int DIM_X = 10;
    public static final int DIM_Y = 10;
    
    // Estado del juego
    private boolean exit = false;
    private int cycles;
    private int deadLemmings = 0;
    private int lemmingsAtExit = 0; 
    private int lemmingsToWin = 2;
    private int lemmingsInBoard;
    
    // Contenedor de objetos
    private GameObjectContainer container;
   
    private int level;
    private GameConfiguration config = new FileGameConfiguration().NONE;
    
    public Game(int level) {
        this.level = level;
        
        reset(level);
    }

    @Override
    public GameObjectContainer getGameObjects() {
        return container;
    }
    
    public void load(String fileName) throws GameLoadException {
    	config = new FileGameConfiguration(fileName, this);
        load(config);
    
    
    
    }
    
    private void load(GameConfiguration config) {
    
        this.cycles = config.getCycle();
        this.lemmingsInBoard = config.numLemmingsInBoard();
        this.deadLemmings = config.numLemmingsDead();
        this.lemmingsAtExit = config.numLemmingsExit();
        this.lemmingsToWin = config.numLemmingsToWin();
        this.container = config.getGameObjects();
        
    }
    
    public void save(String fileName) throws GameModelException {
        try (FileWriter writer = new FileWriter(fileName, false);
             PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.println(Messages.LEMMING_FORMAT.formatted(
                cycles, lemmingsInBoard, deadLemmings, lemmingsAtExit, lemmingsToWin));
        } catch (IOException e) {
            throw new GameModelException();
        }
        getGameObjects().save(fileName);
    }

    @Override
    public void incrementarMuertos() {
        deadLemmings++;
        this.lemmingsInBoard--;
    }
    
    @Override
    public void incremetaLemmingsPuerta() {
        lemmingsAtExit++;
        this.lemmingsInBoard--;
    }

    // Métodos de ciclo de juego
    private void incCiclos() {
        this.cycles++;
    }
    
    @Override
    public int getCycle() {
        return this.cycles;
    }
    
    @Override
    public int numLemmingsInBoard() {
        return lemmingsInBoard;
    }
    
    @Override
    public int numLemmingsDead() {
        return deadLemmings;
    }
    
    @Override
    public int numLemmingsExit() {
        return lemmingsAtExit;
    }
    
    @Override
    public int numLemmingsToWin() {
        return lemmingsToWin;
    }
    
    @Override
    public String positionToString(int col, int row) {
        Position pos = new Position(col, row);
        return container.toString(pos);
    }
     
    @Override
    public boolean playerWins() {
        return lemmingsAtExit >= lemmingsToWin && lemmingsInBoard == 0;
    }
    
    @Override
    public boolean playerLooses() {
        return lemmingsInBoard == 0 && lemmingsAtExit < lemmingsToWin;
    }
    
    @Override
    public boolean isFinished() {
        return exit || playerWins() || playerLooses();
    }
    
    @Override
    public void exit() {
        this.exit = true;
    }
    
    @Override
   public boolean reset(int level) {
        if (this.config == FileGameConfiguration.NONE) {
            int nuevoNivel;
            if (level == -1) {
                nuevoNivel = this.level;
            } else {
                nuevoNivel = level;
            }
            
            switch (nuevoNivel) {
                case 0:
                    init0();
                    this.level = 0;
                    break;
                case 1:
                    init1();
                    this.level = 1;
                    break;
                case 2:
                    init2();
                    this.level = 2;
                    break;
                default:
                    return false; 
            }
        } else {    
            load(this.config);
            return true;
        }
        
        this.cycles = 0;
        this.deadLemmings = 0;
        this.lemmingsAtExit = 0;
        
        return true;
    }
    
    @Override
    public void update() {
        this.incCiclos();
        container.update();
    }
    
    @Override
    public boolean isInAir(Position pos) {
        return container.isSolid(pos);
    }
    
    @Override
    public boolean setRole(LemmingRole role, Position pos) throws OffBoardException {
        if (!pos.estaEnTablero()) {
            throw new OffBoardException(Messages.OFF_WORLD_SETROLE.formatted(pos.toString()));
        }
        return container.setRole(pos, role);
    }
    
    // Inicialización de niveles
    private void init0() {
    	GameObjectContainer container = new GameObjectContainer();
    	this.setGameObjectContainer(container);
    	  this.setNumLemmingsToWin(2);
          this.setNumLemmingsInBoard(3);

    		container.add(new Lemming(new Position(9, 0),  this,new WalkerRole()));
    		container.add(new Lemming(new Position(2, 3), this, new WalkerRole()));
    		container.add(new Lemming(new Position(0, 8), this,new WalkerRole()));
    	
    		//container.add(new MetalWall(new Position(9, 3), game));
    		container.add(new Wall(new Position(9, 1), this));
    		container.add(new Wall(new Position(8, 1), this));
    		container.add(new Wall(new Position(3, 4), this));
    		container.add(new Wall(new Position(2, 4), this));
    		container.add(new Wall(new Position(4, 4), this));
    		container.add(new Wall(new Position(4, 6), this));
    		container.add(new Wall(new Position(5, 6), this));
    		container.add(new Wall(new Position(6, 6), this));
    		container.add(new Wall(new Position(7, 6), this));
    		container.add(new Wall(new Position(7, 5), this));
    		container.add(new Wall(new Position(0, 9), this));
    		container.add(new Wall(new Position(1, 9), this));
    		container.add(new Wall(new Position(9, 9), this));
    		container.add(new Wall(new Position(8, 9), this));
    		container.add(new Wall(new Position(8, 8), this));
    		container.add(new ExitDoor(new Position(4, 5), this));
    		
    		
    	}
    private void init1() {
    		GameObjectContainer container = new GameObjectContainer();
    		this.setGameObjectContainer(container);
    		container.add(new Lemming(new Position(9, 0), this, new WalkerRole()));
        container.add(new Lemming(new Position(2, 3), this, new WalkerRole()));
    	  container.add(new ExitDoor(new Position(4, 5), this));
    	   container.add(new Lemming(new Position(3, 3), this, new WalkerRole()));
    		   container.add(new Lemming(new Position(0, 8), this, new WalkerRole()));
    		       container.add(new Wall(new Position(9, 1), this));
    		   // container.add(new MetalWall(new Position(8, 2), game));
    		    //container.add(new Wall(new Position(9, 5), game));
    		       this.setNumLemmingsToWin(2);
    		       this.setNumLemmingsInBoard(4);

    		    container.add(new Wall(new Position(8, 1), this));
    		    container.add(new Wall(new Position(3, 4), this));
    		    container.add(new Wall(new Position(2, 4), this));
    		    container.add(new Wall(new Position(4, 4), this));
    	    container.add(new Wall(new Position(4, 6), this));
    	    container.add(new Wall(new Position(5, 6), this));
    		    container.add(new Wall(new Position(6, 6), this));
    		    container.add(new Wall(new Position(7, 6), this));
    		    container.add(new Wall(new Position(7, 5), this));
    		    container.add(new Wall(new Position(0, 9), this));
    		    container.add(new Wall(new Position(1, 9), this));
    		    container.add(new Wall(new Position(9, 9), this));
    		    container.add(new Wall(new Position(8, 9), this));
    		    container.add(new Wall(new Position(8, 8), this));

    			
    	}
    	private void init2() {
    		GameObjectContainer container = new GameObjectContainer();
    		this.setGameObjectContainer(container);
    		  this.setNumLemmingsToWin(2);
    	      this.setNumLemmingsInBoard(6);
    		 container.add(new Lemming(new Position(9, 0), this, new WalkerRole()));
    		 container.add(new Lemming(new Position(2, 3), this, new WalkerRole()));
    		  container.add(new ExitDoor(new Position(4, 5), this));
    		   container.add(new Lemming(new Position(3, 3), this, new WalkerRole()));
    		   container.add(new Lemming(new Position(0, 8), this, new WalkerRole()));
    		     container.add(new Wall(new Position(9, 1), this));
    		
    		 
    	

    		    
    		    
    		    container.add(new Lemming(new Position(6, 0), this, new WalkerRole()));
    		    container.add(new Lemming(new Position(6, 0), this ,new ParachuterRole()));
    		    container.add(new Wall(new Position(8, 1), this));
    		    container.add(new Wall(new Position(3, 4), this));
    		    container.add(new Wall(new Position(2, 4), this));
    		    container.add(new Wall(new Position(4, 4), this));
    		    container.add(new Wall(new Position(4, 6), this));
    		    container.add(new Wall(new Position(5, 6), this));
    		    container.add(new Wall(new Position(6, 6), this));
    		    container.add(new Wall(new Position(7, 6), this));
    		    container.add(new Wall(new Position(7, 5), this));
    		    container.add(new Wall(new Position(0, 9), this));
    		    container.add(new Wall(new Position(1, 9), this));
    		    container.add(new Wall(new Position(9, 9), this));
    		    container.add(new Wall(new Position(8, 9), this));
    		    container.add(new Wall(new Position(8, 8), this));
    		    container.add(new Wall(new Position(3, 5), this));
    		    container.add(new MetalWall(new Position(3, 6), this));
    		   
    	}

    @Override
    public void setNumLemmingsToWin(int numLemmingsToWin) {
        this.lemmingsToWin = numLemmingsToWin;
    }

    @Override
    public void setGameObjectContainer(GameObjectContainer gameObjectContainer) {
        this.container = gameObjectContainer;
    }

    @Override
    public void setNumLemmingsInBoard(int numLemmingsInBoard) {
        this.lemmingsInBoard = numLemmingsInBoard;
    }

    @Override
    public void setNumLemmingsDead(int numLemmingsDead) {
        this.deadLemmings = numLemmingsDead;
    }
}