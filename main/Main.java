package main;
	
import java.io.IOException;

import callback.ChangeCallback;
import guipack.Controller;
import guipack.DialogController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;


public class Main extends Application implements ChangeCallback{
	Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("../guipack/StartDialog.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,250,150);
			primaryStage.setScene(scene);
			primaryStage.show();
			this.stage=primaryStage;
			
			DialogController controller=loader.<DialogController>getController();
			controller.setCallback(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void change(String a, String b) {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("../guipack/HangmanGUI.fxml"));
			Parent root;
			root = loader.load();
			Scene scene = new Scene(root,640,470);
			stage.setScene(scene);
			stage.show();
			
			Controller controller=loader.<Controller>getController();
			controller.setScene(scene);
			controller.init(a,b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
