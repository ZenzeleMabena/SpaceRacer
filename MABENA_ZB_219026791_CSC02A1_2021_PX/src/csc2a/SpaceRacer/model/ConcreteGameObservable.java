package csc2a.SpaceRacer.model;

import java.util.ArrayList;


/**
 * This class serves as a subject that is being observed for changes by its observers
 * @author ZB MABENA 219026791
 * @see ConcreteGameObserver
 *@version PX
 */

public class ConcreteGameObservable implements IGameObservable {
	
	
	
	///observers observing this current subject
	private ArrayList<IGameObserver> observers;
	private GameWorld game;
	private boolean changed = false;
	
	
	
	public ConcreteGameObservable()
	{
		
		observers = new ArrayList<>();
		game = new GameWorld(true);
	
	}
	
	
	
/**
 * Method for adding new observers to this current instance of the subject
 */
	
	public void addObserver(IGameObserver observer) {
		
		if(observer != null)
		{
			this.observers.add(observer);
		}
		
		
		
		
	}
	
	public void isChanged()
	{
		this.changed = true;
	}


	public void removeObserver(IGameObserver observer) {
		
		///remove the specified observer
		this.observers.remove(observer);
		
	}
	/**
	 * Method for notifying all observing observers when a change has occured
	 */


	public void notifyObservers() {
		
		if(!changed) {return;}
		
		this.changed = false;
		
		
		for(IGameObserver o : observers)
		{
			
			o.update();
			
		}
		
		
		
	}
	
	
	public GameWorld getGame()
	{
		
		
		return this.game;
	}
	

	@Override
	public GameWorld getUpdate(IGameObserver observer) {
			
		return this.game;
	}

}
