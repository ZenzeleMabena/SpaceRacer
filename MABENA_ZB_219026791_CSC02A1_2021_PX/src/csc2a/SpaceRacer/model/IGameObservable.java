package csc2a.SpaceRacer.model;

public interface IGameObservable {
	
	
	///Default functions for adding and removing observers from the observable
	public void addObserver(IGameObserver observer);
	public void removeObserver(IGameObserver observer);
	
	
	///this function will be responsible for notifying observers of any change inside the
	public void notifyObservers();
	
	///method for returning the updated game world to its observers
	public GameWorld getUpdate(IGameObserver observer);

}
