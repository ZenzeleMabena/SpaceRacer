package csc2a.SpaceRacer.model;



import java.util.ArrayList;

import java.util.Random;

import csc2a.SpaceRacer.file.TextFileHandler;

public class GameWorld  {
	
	
	
	private SpaceCar theCar;
	private ArrayList<Meteor> meteors;
    private Score score;
    private boolean won = false;
    private boolean lost = false;
    

    
	
	
	public GameWorld()
	{
		
		theCar = new SpaceCar();
		meteors = new ArrayList<>();
		score = new Score(0,"");
		fillMeteors();
	
		
		

		
	}
	/**
	 * Basic args constructor
	 * @param fileName The purpose of this parameter is to differentiated the two constructors
	 */
	public GameWorld(boolean selector)
	{
		
		
		theCar = new SpaceCar();
		meteors = TextFileHandler.readMeteors();
		score = new Score(80000l,"");
		

	}

	
	/**
	 * This function will be solely responsible for detecting whether the car has collided with any of the meteors
	 * if so,then the the game loop will end
	 */
	public void lost()
	{
		///this method will check for collisions and determined whether the player has lost or not
		
		this.lost = true;
	}
	
	
	public void won()
	{
		
		
		this.won = true;
	}
	/**
	 * @return the won
	 */
	public boolean isWon() {
		return won;
	}
	/**
	 * @return the lost
	 */
	public boolean isLost() {
		return lost;
	}

	/**
	 * 
	 * this function will be responsible for filling/initializing meteors inside the meteor arraylist
	 */
	
	public void fillMeteors()
	{
		
		Meteor NewMeteor;
		Random random = new Random();
		
		for(int i = 0;i < 28;i++)
		{
			NewMeteor = new Meteor(random.nextInt(100));
			
			this.meteors.add(NewMeteor);
		}
		
	}
	
	
	/**
	 * @return the theCar
	 */
	public SpaceCar getTheCar() {
		return theCar;
	}

	/**
	 * @return the meteors
	 */
	public ArrayList<Meteor> getMeteors() {
		return meteors;
	}

	/**
	 * @return the score
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * @param theCar the theCar to set
	 */
	public void setTheCar(SpaceCar theCar) {
		this.theCar = theCar;
	}


	/**
	 * @param score the score to set
	 */
	public void setScore(Score score) {
		this.score = score;
	}


}
