package csc2a.SpaceRacer.ui;

import java.io.FileInputStream;




import java.io.IOException;
import java.util.Random;

//import csc2a.SpaceRacer.file.BinaryFileHandler;
import csc2a.SpaceRacer.model.ConcreteGameObservable;
import csc2a.SpaceRacer.model.GameWorld;
//import csc2a.SpaceRacer.model.IGameObservable;
import csc2a.SpaceRacer.model.IGameObserver;
import csc2a.SpaceRacer.model.Meteor;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * The purpose of this class is to observe any changes happening to its subject and update itself
 * @author ZB MABENA 219026791
 *@version PX
 *@see ConcreteGameObservable
 */

public class ConcreteGameObserver implements IGameObserver {
	
	
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private ConcreteGameObservable subject;
	private GameWorld game;
	private Stage gameStage;
	private Scene gameScene;
	private Pane gamePane;
	private Image backgroundImage;
	private ImageView car;
	
	
	///GridPanes for moving background
	private GridPane gridPane1;
	private GridPane gridPane2;
	
	
	//Booleans for knowing what action the car should perform
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private boolean isUpPressed;
	
	private int angle;///angle that the car tilts when moving side to side
	private AnimationTimer timer;///animation timer for animation inside game
	
	
    static final int CAR_RADIUS = 26;
    static final int METEOR_RADIUS = 20;
    
    
    
    /**
     * Basic observer constructor
     * @param subject This is the subject that will be obesevred for changes
     * @see ConcreteGameObserver
     */
	
	
	public ConcreteGameObserver(ConcreteGameObservable subject)
	{
		
		
		///instantiate the new game 
		game = new GameWorld(true);
		///assign the passed subject to the current instance subject
		this.subject = subject;
		///adding this current observer to the subject passed in
		subject.addObserver(this);
		
		///instantiating the pane to be used
		gamePane = new Pane();
	
		
		
		
		
		///get information from game and set the nodes according to the things inside the game
		gameScene = new Scene(gamePane,WIDTH,HEIGHT);
		
		initPane();
		makeGameLoop();
		gameStage = new Stage();
		
		gameStage.setScene(gameScene);
		
		
	
		
		
		
	}
	
	
	
	
   /**
    * Overridden update function completing the design pattern 
    */
	
	public void update() 
	{

		///assigning the updated game world to the current observer's game world
		this.game = this.subject.getGame();
		
	
				
    }
	/**
	 * 
	 * @return returns the current instance's game stage
	 */
	
	public Stage getGameStage()
	{
		return this.gameStage;
	}
	
	
	/**
	 * Helper function that helps with initializing the game scene of the game
	 */
	public void initPane()
	{
		initImage();
    	
    	
    	
    	
    	Pane stack = new Pane();
    	
    	this.car = new ImageView(this.game.getTheCar().getCar_Image());
    	
    	car.setLayoutX(this.game.getTheCar().getPositionX());
    	car.setLayoutY(this.game.getTheCar().getPositionY());
    	
    	
    	Button btnControls = new Button("Controls");
    	Button btnInstruc = new Button("Instructions");
    	HBox container = new HBox();
    	
    	container.getChildren().addAll(btnControls,btnInstruc);
    	
    	
    	btnInstruc.setOnAction(event -> {
    		
    		
    		Alert instructions = new Alert(AlertType.INFORMATION);
    		String content = new String("The goal of this game is to reach the top of the screen while avoiding incoming medeorites\n" + 
    		"The faster you get to the top of the screen the higher your score is going to be\n" );
    		instructions.setContentText(content);
    		instructions.show();
    		
    	});
    	
    	btnControls.setOnAction(event -> {
    		    		
    		Alert controls = new Alert(AlertType.INFORMATION);
    		String content = new String("MOVE UP : W\n" + "MOVE RIGHT : D\n" + "MOVE LEFT : A\n");
    		controls.setContentText(content);
    		controls.show();
    		
    		
    	});
    	
    	
        createKeyListeners();
    	
    	
    	createBackground(stack);
    	
    	
    	stack.getChildren().add(car);

    	for(Meteor m : this.game.getMeteors())
    	{
    		stack.getChildren().add(m.getMeteor());
    		
    	}
        
    	
    	
    	this.gamePane.getChildren().add(stack);
    	this.gamePane.getChildren().add(container);
 
	}
	
	/**
	 * this function helps with creating the background of the game
	 * @param pane this will be the pane that will be used as the background of the game
	 */
	
	private void createBackground(Pane pane)
	{
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		for(int i = 0;i < 12;i++)
		{
			ImageView background1 = new ImageView(backgroundImage);
			ImageView background2 = new ImageView(backgroundImage);
			GridPane.setConstraints(background1,i % 3,i/3);
			GridPane.setConstraints(background2,i % 3,i/3);
			gridPane1.getChildren().add(background1);
			gridPane2.getChildren().add(background2);
			
		}
		gridPane2.setLayoutY(-1024);
		pane.getChildren().addAll(gridPane1,gridPane2);
		
	}
	
	/**
	 * Helper fucntion for detecting collision
	 * @return returns true if there was a collison between any of the meteors and the car
	 */
	private boolean CheckForCollision()
	{
		for(int i = 0;i < this.game.getMeteors().size();i++)
		{
			if(METEOR_RADIUS + CAR_RADIUS > distanceBetween((int)car.getLayoutX() ,(int)this.game.getMeteors().get(i).getMeteor().getLayoutX(),
					(int) car.getLayoutY() ,(int)this.game.getMeteors().get(i).getMeteor().getLayoutY()))
			{
				return true;
			}
		}
		
		
		return false;
	}
	
	
	/**
	 * 
	 * @param intX1 x position of the node
	 * @param intX2 x position of the second node
	 * @param intY1 y position of the first node
	 * @param intY2 y position of the second node
	 * @return returns the distance between the two nodes
	 */
	
	private int distanceBetween(int intX1,int intX2,int intY1,int intY2)
	{
		return (int) Math.sqrt(Math.pow(intX1-intX2, 2)+ Math.pow(intY1-intY2,2));
	}

	
	
	/**
	 * Helper function for animating the moving meteors inside the game world
	 */
	private void moveGameElements()
	{
		for(Meteor m : this.game.getMeteors())
		{
			if(m.getMeteor().getLayoutY() > 500 )	
			{
				Random randGen = new Random();
				m.getMeteor().setLayoutY(m.getMeteor().getLayoutY() -750);
				m.getMeteor().setLayoutX(randGen.nextInt(800));
			}
			else
			{
			m.getMeteor().setLayoutY(m.getMeteor().getLayoutY() +2);
			m.getMeteor().setRotate(m.getMeteor().getRotate() +2);
			}
		}
	}
	
	
	/**
	 * Function for moving the background of the game 
	 */
	
	private void moveBackground()
	{
		gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.8);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.8);
		
		if(gridPane1.getLayoutY() >= 1024)
		{
			gridPane1.setLayoutY(-1024);
		}
		if(gridPane2.getLayoutY() >= 1024)
		{
			gridPane2.setLayoutY(-1024);
		}
		
	}
	
	
	/**
	 * Function for creating listeners for key presses to move the car inside the game
	 */
	private void createKeyListeners()
	{
		this.gameScene.setOnKeyPressed((EventHandler<? super KeyEvent>) new EventHandler<KeyEvent>() {
			
			public void handle(KeyEvent event)
			{
				if(event.getCode() == KeyCode.W)
				{
					isUpPressed = true;
					
					
				}
				else if(event.getCode() == KeyCode.A)
				{
					isLeftPressed = true;
				
				}
				else if(event.getCode() == KeyCode.D)
				{
					isRightPressed = true;
					
					
				}
				
			}
			
		});
		
		
		
		this.gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.W)
				{
					isUpPressed = false;
				}
				else if(event.getCode() == KeyCode.A)
				{
					isLeftPressed = false;
				}
				else if(event.getCode() == KeyCode.D)
				{
					isRightPressed = false;
					
				}
				
			}
			
		});
		
		
		
		
		
		
	}
	
	/**
	 * Function responsible for handling the game loop 
	 */
	private void makeGameLoop()
	{
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				moveCar();
				moveBackground();
				moveGameElements();
				checkConditions();
				keepScore();
				
			}
			
		};
		
		
		timer.start();
	}
	/**
	 * Function that checks if any terminating conditions have been 
	 * this function is repeatedly called inside the game loop
	 */
	private void checkConditions()
	{
		
		///check there were any collisions
		
		if(CheckForCollision())
		{
			this.game.isLost();
			
			
			Alert ScoreBoard = new Alert(AlertType.INFORMATION);
			ScoreBoard.setHeaderText("Score");
			
			if(this.game.isLost())
			{
				
				this.timer.stop();
			    ScoreBoard.setContentText("YOU LOST! \n" + "Your score is : " + this.game.getScore().getScore().get());
			     ScoreBoard.show();
			     
			     
			 // BinaryFileHandler.saveTopScores(this.game.getScore());
			  
			  gameStage.close();
			}
		
			
		}
		
		
		
		
		
		
		
		
		
		///or check if the player reached the top
		if(this.car.getLayoutY() ==0)
		{
			this.game.won();
			Alert ScoreBoard = new Alert(AlertType.INFORMATION);
			ScoreBoard.setHeaderText("Score");
			
			this.timer.stop();
			ScoreBoard.setContentText("YOU WON! \n" + "Your score is : " + this.game.getScore().getScore().get());
		    ScoreBoard.show();
		    ///TODO : remember to ask for the users name so that you can save the score to our binary file
		    //BinaryFileHandler.saveTopScores(this.game.getScore());
		    gameStage.close();
		}
		
		
		
	}
	
	/**
	 * function for keeping score of the player
	 */
	
	
	private void keepScore()
	{
	     
		if(!this.game.isLost())
		{
			this.game.getScore().getScore().set(this.game.getScore().getScore().get() -(10));
		}
		else if(!this.game.isWon())	
		{
			
			this.game.getScore().getScore().set(this.game.getScore().getScore().get() -(10));
			
		}
	     

	}
	
	/**
	 * Function for moving the car when a key is pressed
	 */
	
	
	private void moveCar()
	{
		if(isLeftPressed && !isUpPressed && !isRightPressed)
		{
			
			///when the left key is pressed
			if(angle > -30)
			{
				angle-=5;
			}
			///setting some rotation of the car to make it a bit more realistic
			this.car.setRotate(angle);
			
			if(this.car.getLayoutX() > 0)
			{
			      ///first change state of contained game world inside the subject being observed
				this.subject.getGame().getTheCar().moveCarLeft();
				this.subject.isChanged();
				///then update the observers
				this.subject.notifyObservers();
				///then you want to move the ImageView based on the updated game world contained by the observers
				this.car.setLayoutX(game.getTheCar().getPositionX());
			}
			
			
			
			
		}
		if(!isLeftPressed && !isUpPressed && isRightPressed) 
		{
			///when only the right key is pressed
			if(angle < 30)
			{
				angle +=5;
			}
			this.car.setRotate(angle);
			
			if(this.car.getLayoutX() < WIDTH-60)
			{
			      ///first change state of contained game world inside the subject being observed
						this.subject.getGame().getTheCar().moveCarRight();
						this.subject.isChanged();
						///then update the observers
						this.subject.notifyObservers();
						///then you want to move the ImageView based on the updated game world contained by the observers
						this.car.setLayoutX(game.getTheCar().getPositionX());
				
			}
			
			
		}
		if(isUpPressed && !isLeftPressed && !isLeftPressed)
		{
			///when only the up key is pressed
			if(this.car.getLayoutY() > 0)
			{
				
				///first change state of contained game world inside the subject being observed
				this.subject.getGame().getTheCar().moveCarUp();
				this.subject.isChanged();
				///then update the observers
				this.subject.notifyObservers();
				///then you want to move the ImageView based on the updated game world contained by the observers
				this.car.setLayoutY(this.game.getTheCar().getPositionY());
				
			}
			
		}
		if(isUpPressed && isLeftPressed && isRightPressed)
		{
			///all keys are pressed
			///And I want to do nothing but reset the angle at which the race car might be tilted at
			if(angle < 0)
			{
				angle+=5;
			}
			if(angle > 0)
			{
				angle-=5;
			}
			
			
			this.car.setRotate(angle);
		}
		if(!isUpPressed && !isLeftPressed && !isRightPressed)
		{
			///all keys are not pressed
			///when nothing is pressed then I want to just reset the angle of the car
			if(angle < 0)
			{
				angle+=5;
			}
			if(angle > 0)
			{
				angle-=5;
			}
			
			
			this.car.setRotate(angle);
		}
		
		
		
		
		
	}
	
	/**
	 * The sole purpose of this helper function is to read in an image and handle any errors that come with
	 */
	
	
    private void initImage()
    {
   
    	try
    	{
    		this.backgroundImage = new Image(new FileInputStream("data\\Game Background.png"),WIDTH,HEIGHT,false,true);
 
    		
    	}
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    	
    	
    	
    }
    

}
