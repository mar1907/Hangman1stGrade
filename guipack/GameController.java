package guipack;

import model.DataAcquisition;
import model.Word;

public class GameController {

	private int letters;
	private int diff;
	private String availableLetters="alabala".toUpperCase();
	private Word word;
	private DataAcquisition dA;
	
	public GameController(int letters, int diff) {
		this.letters=letters;
		this.diff=diff;
		createAvailableLetters(letters);
		dA=new DataAcquisition();
		word=dA.getWord(letters);
	}
	
	private void createAvailableLetters(int letters) {
		if(letters==1) {
			availableLetters="aeioumtcsd";
		}
		else if(letters==2) {
			availableLetters="aeioumtcsd"+"\n"+"bfghjlnprv";
		}
		if(letters==3) {
			availableLetters="abcdefghijklmn"+"\n"+"opqrstuvwxyz";
		}
	}
	
	public String getAvailableLetters() {
		return availableLetters;
	}
	
	public Object[] sendLetter(String letter) {
		Object[] returnStruct=new Object[4];
		String retWord=word.checkLetter(letter.toLowerCase().charAt(0));
		if(retWord!=null) {
			returnStruct[0]=new String(retWord);
		}
		if(availableLetters.toUpperCase().contains(letter)) {
			returnStruct[1]=new Boolean(true);
			availableLetters=availableLetters.replace(letter.toLowerCase(), "");
		} else {
			returnStruct[1]=new Boolean(false);
		}
		if(retWord!=null) {
			returnStruct[2]=new Boolean(true);
		} else {
			returnStruct[2]=new Boolean(false);
		}
		if(word.getCurrentState().contains("_")) {
			returnStruct[3]=new Boolean(false);
		} else {
			returnStruct[3]=new Boolean(true);
		}
		return returnStruct;
	}
	
	public String getWord() {
		return word.getCurrentState();
	}
	
}
