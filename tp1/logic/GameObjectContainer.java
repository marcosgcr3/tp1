package tp1.logic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import  tp1.exceptions.GameModelException;

import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;

import tp1.logic.lemmingRoles.LemmingRole;


public class GameObjectContainer {
	
	
	
	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}
	
	

    public void add(GameObject object) { 
    	objects.add(object);
    	
    } 
    
    public boolean isSolid(Position pos) {
    	boolean isSolid = false;
		for(GameObject g : objects) {
			if(g.isInPosition(pos) && g.isSolid()) {	
			return isSolid =true;
			
			}
		}
		return isSolid;
	}
    public String toString(Position position) {
    	 StringBuilder result = new StringBuilder();
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			
			if (object.isInPosition(position)) {
				  result.append(object.getIcon());
			}
		}
		return result.toString();
	}
  
   
    public boolean setRole(Position pos, LemmingRole role) {
    	boolean set = false;
    	for(GameObject g : objects) {
			if(g.isInPosition(pos) ) {	
				return set =g.setRole(role);
			}
			
		}
		return set;
    	
    }
  

	public void save(String fileName) throws GameModelException{ 

		try (FileWriter escritor = new FileWriter(fileName, true);  PrintWriter printWriter = new PrintWriter(escritor)) {
			 
			
			for(GameObject g : objects) {
				printWriter.println(g.toString());
				
			}
		} catch (IOException e) {
			throw new GameModelException();
        }
	}

	public void removeMuertos() { 
	    // Iterar de atrás hacia adelante para eliminar lemmings muertos o que han tocado la puerta
	    for (int i = objects.size() - 1; i >= 0; i--) {
	        GameObject obj = objects.get(i);

	     

	            if (!obj.isAlive() ) { 
	            	
	            	 objects.remove(i);
	            }
	        
	    }
	}
	public boolean receiveInteractionsFrom(GameItem obj) {
	   boolean receive = false;
	    for (GameObject gameObject : objects) {
	        if (gameObject.receiveInteraction(obj)) {
	           return receive =true;
	        }
	    }
	    return receive;
	}
    public void update() {
    	
        for (GameObject object : objects) {
        	if(!object.isSolid()) {
        		receiveInteractionsFrom(object);
        	}
        	
        	
            object.update();
           
        }removeMuertos();
    }
    
    
}
