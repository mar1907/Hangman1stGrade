package guipack;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Controller {
	
	@FXML
	private Label diffLabel;
	
	@FXML
	private Label attemptsLabel;
	
	@FXML
	private TextArea availableLetters;
	
	@FXML
	private TextArea wordSoFar;
	
	@FXML
	private ImageView imageBox;
	
	private Scene scene;
	private GameController gc;
	private int diff, attempts;
	private int pic=0;
	private boolean gameEnded;
	
	static private int[] easypics= {0,1,2,3,4,5,6,7};
	static private int[] mediumpics= {0,1,2,4,6,7};
	static private int[] hardpics= {0,2,4,7};
	
	public void setScene(Scene scene) {
		this.scene=scene;
		scene.addEventFilter(KeyEvent.KEY_PRESSED,
                event -> keyRegistered(event.getCode().toString()));
	}
	
	public void init(String a, String b) {
		int letters=1;
		if(a.equals("Primele 10")) {
			letters=1;
		}
		else if(a.equals("Primele 20")) {
			letters=2;
		}
		else {
			letters=3;
		}
		
		if(b.equals("Usor")) {
			diff=7;
		}
		else if(b.equals("Mediu")) {
			diff=5;
		}
		else {
			diff=3;
		}
		attempts=diff;
		gc=new GameController(letters, diff);
		
		diffLabel.setText(b);
		attemptsLabel.setText(""+attempts);
		availableLetters.setText(gc.getAvailableLetters().toUpperCase());
		wordSoFar.setText(gc.getWord());
	}
	
	public void keyRegistered(String key) {
		if(gameEnded||attempts==0) return;
		Object[] returnStruct=gc.sendLetter(key);
		availableLetters.setText(gc.getAvailableLetters().toUpperCase());
		if((boolean) returnStruct[1]) {
			if((boolean) returnStruct[2]) {
				wordSoFar.setText((String) returnStruct[0]);
				if((boolean) returnStruct[3]) {
					gameEnded=true;
				}
			}else {
				changePic();
			}
		}
	}
	
	public void changePic() {
		if(gameEnded||attempts==0) return;
		attempts--;
		attemptsLabel.setText(""+attempts);
		pic++;
		int end=0;
		try {
			switch(diff) {
				case 7:	end=easypics[pic];
						break;
				case 5:	end=mediumpics[pic];
						break;
				case 3:	end=hardpics[pic];
						break;
			}
		}
		catch(ArrayIndexOutOfBoundsException ex) {}
		String name="file:../../hang"+end+".png";
		imageBox.setImage(new Image(name));
	}
	
	@FXML
	void closeGame() {
		System.exit(0);
	}
	
}
