package csc2a.SpaceRacer.ui;

import java.io.FileInputStream;
import java.io.IOException;
import csc2a.SpaceRacer.model.ConcreteGameObservable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameViewManager {
	
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private BorderPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Image backgroundImage;
	private ConcreteGameObserver observer;
	private ConcreteGameObservable observable;
	
	
	
	
	
	public GameViewManager()
	{
		observable = new ConcreteGameObservable();
		observer = new ConcreteGameObserver(observable);
		
		
		mainPane = new BorderPane();
		
		initPane();     //// this function will basically just put everything where it needs to be on the pane
		
		
		mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		
		
		
	}
	
	
	public Stage getMainStage()
	{
		return this.mainStage;
		
	}
	
    private  void initPane()
    {
    	///add background
    	
    	initImage();
    	ImageView view = new ImageView(this.backgroundImage);
    	
   
    
    	
    	Text title = new Text("Space Racer");
    	title.setFont(Font.font("VERDANA", 80));
    	title.setFill(Color.SKYBLUE);
    	title.setLayoutX(400);
    	title.setLayoutY(400);
    	
    	
    	
    	StackPane stack = new StackPane();
    	stack.getChildren().addAll(view,title);
    	
    	Button btnStart = new Button("Play");
    	Button btnInstruc = new Button("Instructions");
    	Button btnControls = new Button("Controls");
    	
    	btnStart.setOnAction(event ->{
    		
    		
    		mainStage.hide();
    		observer.getGameStage().show();
    		
    	});
    	
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
    	

    	
    	HBox container = new HBox();
    	container.getChildren().addAll(btnStart,btnInstruc,btnControls);
    	
    	
    	title.setLayoutX(400);
    	title.setLayoutY(400);
    	
    	
    	this.mainPane.setTop(container);
    	this.mainPane.setCenter(stack);
    	
    	
    
    	
    	///Also dont forget to add action listeners for keys and shiit
    	
    	
    }
    
    private void initImage()
    {
   
    	try
    	{
    		this.backgroundImage = new Image(new FileInputStream("data\\Main Cover.jpg"),WIDTH,HEIGHT,false,true);
 
    		
    	}
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    	
    	
    	
    }
    

	
	

}
