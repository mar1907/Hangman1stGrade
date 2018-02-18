package guipack;

import callback.ChangeCallback;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

@SuppressWarnings("rawtypes")
public class DialogController {
	
	private ChangeCallback callback;
	
	@FXML
	private ComboBox lettersComboBox;
	
	@FXML
	private ComboBox difficultyComboBox;
	
	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() {
	    lettersComboBox.getItems().removeAll(lettersComboBox.getItems());
	    lettersComboBox.getItems().addAll("Primele 10", "Primele 20", "Toate");
	    lettersComboBox.getSelectionModel().select("Primele 10");
	    
	    difficultyComboBox.getItems().removeAll(lettersComboBox.getItems());
	    difficultyComboBox.getItems().addAll("Usor", "Mediu", "Greu");
	    difficultyComboBox.getSelectionModel().select("Mediu");
	    
	}
	
	public void setCallback(ChangeCallback callback) {
		this.callback=callback;
	}
	
	@FXML
	public void startGame() {
		callback.change((String)lettersComboBox.getValue(),(String)difficultyComboBox.getValue());
	}

}
