package csc2a.SpaceRacer.model;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.image.Image;

public class SpaceCar {
	
	
	
	private static int CAR_WIDTH = 55;
	private static int CAR_HEIGHT = 75;
	private double positionX;
	private double positionY;

	
	
	private boolean movedRight;
	private boolean movedLeft;
	private boolean movedUp;
	
	
	private Image car_Image;
	
	
	public SpaceCar()
	{
		this.positionX = 800/2;
		this.positionY = 600-CAR_HEIGHT;
		initCar();
		this.movedRight = false;
		this.movedLeft = false;
		this.movedUp = false;
		
		
		
	}
	
	public void initCar()
	{
		try
    	{
    		this.car_Image = new Image(new FileInputStream("data\\Car.png"),CAR_WIDTH,CAR_HEIGHT,false,true);
 
    		
    	}
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    	
    	
		
		
		
	}
	
	
    public void moveCarRight()
    {
    	this.movedRight = true;
        this.setPositionX(positionX + 3);
    	
    }
    
    public void moveCarLeft()
    {
    	this.movedLeft = true;
        this.setPositionX(positionX -3);
    }
    
    
    
    public void moveCarUp()
    {
    	
    	this.movedUp = true;
    	this.setPositionY(positionY - 3);
    }


	/**
	 * @return the positionX
	 */
	public double getPositionX() {
		return positionX;
	}

	/**
	 * @return the positionY
	 */
	public double getPositionY() {
		return positionY;
	}

	/**
	 * @return the car_Image
	 */
	public Image getCar_Image() {
		return car_Image;
	}

	/**
	 * @param positionX the positionX to set
	 */
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	/**
	 * @return the movedRight
	 */
	public boolean isMovedRight() {
		return movedRight;
	}

	/**
	 * @return the movedLeft
	 */
	public boolean isMovedLeft() {
		return movedLeft;
	}

	/**
	 * @return the movedUp
	 */
	public boolean isMovedUp() {
		return movedUp;
	}

	/**
	 * @param movedRight the movedRight to set
	 */
	public void setMovedRight(boolean movedRight) {
		this.movedRight = movedRight;
	}

	/**
	 * @param movedLeft the movedLeft to set
	 */
	public void setMovedLeft(boolean movedLeft) {
		this.movedLeft = movedLeft;
	}

	/**
	 * @param movedUp the movedUp to set
	 */
	public void setMovedUp(boolean movedUp) {
		this.movedUp = movedUp;
	}



}
