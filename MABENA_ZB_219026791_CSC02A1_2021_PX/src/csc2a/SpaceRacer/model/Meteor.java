package csc2a.SpaceRacer.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Meteor {
	
	

	private int  meteorPositionX;
	private int meteorPositionY;
	

	
	private ImageView meteor;
	
	
	public Meteor()
	{
		
		Random randGen = new Random();
		meteorPositionX = randGen.nextInt(30);
		meteorPositionY  = -1024;
	
		
	}
	
	
	public Meteor(int picker)
	{
		
	
		Random randGen = new Random();
		///making the x position of the meteor be random 
		this.meteorPositionX = randGen.nextInt(800);
		this.meteorPositionY  = randGen.nextInt(500)* -1;
		
		///Choose the type of meteor to put inside the image View
		if(picker % 2 == 0)
		{
			initImage1(this.meteorPositionX,this.meteorPositionY);
		}
		else
		{
			initImage2(this.meteorPositionX,this.meteorPositionY);
		}
		
		
		
		
	}
	
	public Meteor(int picker,int intPosX,int intPosY)
	{
		this.meteorPositionX = intPosX;
		this.meteorPositionY = intPosY;
		
		
		///Choose the type of meteor to put inside the image View
		if(picker % 2 == 0)
		{
			initImage1(this.meteorPositionX,this.meteorPositionY);
		}
		else
		{
			initImage2(this.meteorPositionX,this.meteorPositionY);
		}	
	}
	
	/**
	 * @return the meteorPositionX
	 */
	public double getMeteorPositionX() {
		return meteorPositionX;
	}


	/**
	 * @return the meteorPositionY
	 */
	public double getMeteorPositionY() {
		return meteorPositionY;
	}


	/**
	 * @return the meteor
	 */
	public ImageView getMeteor() {
		return meteor;
	}


	/**
	 * @param meteorPositionX the meteorPositionX to set
	 */
	public void setMeteorPositionX(int meteorPositionX) {
		this.meteorPositionX = meteorPositionX;
	}


	/**
	 * @param meteorPositionY the meteorPositionY to set
	 */
	public void setMeteorPositionY(int meteorPositionY) {
		this.meteorPositionY = meteorPositionY;
	}


	public  void initImage1(int xPos,int yPos)
	{
    	try
    	{
    		this.meteor = new ImageView(new Image(new FileInputStream("data\\meteor1.png"),256,256,false,true));
    		this.meteor.setLayoutX(xPos);
    		this.meteor.setLayoutY(yPos);
 
    		
    	}
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    	
    	
		
	}
	
	public  void initImage2(int xPos,int yPos)
	{
		
	  	try
    	{
    		this.meteor = new ImageView(new Image(new FileInputStream("data\\meteor1.png"),256,256,false,true));
    		this.meteor.setLayoutX(xPos);
    		this.meteor.setLayoutY(yPos);
 
    		
    	}
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    	
    	
		
		
	}
	
	public void moveMeteor()
	{
		
		
		if(this.meteorPositionY > 600)
		{
			this.meteorPositionY = -1000;
		}
		
		this.meteorPositionY = this.meteorPositionY + 30;
	}
	
	
	
	
	
	

}
