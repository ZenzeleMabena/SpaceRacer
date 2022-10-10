

import csc2a.SpaceRacer.ui.GameViewManager;

import javafx.application.Application;

import javafx.stage.Stage;



public class Main extends Application{
	
	
	public static void main(String[] args)
	{
		
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GameViewManager manager = new GameViewManager();
		
		primaryStage = manager.getMainStage();
		
		primaryStage.setTitle("Space Racer");
		primaryStage.show();
		
		
	}
	
	
	

}
