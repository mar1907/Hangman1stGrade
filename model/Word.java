package model;

public class Word {
	private String word;
	private StringBuilder currentState;
	
	public Word(String word) {
		this.word = word;
		currentState = new StringBuilder(word);
		for (int i = 1; i<currentState.length() - 1; i++){
			currentState.setCharAt(i, '_');
		}
	}
	
	public String getCurrentState(){
		return currentState.toString();
	}
	
	public String checkLetter(char c){
		if (!word.contains(String.valueOf(c).toLowerCase()))
			return null;
		for (int i = 1; i<currentState.length() - 1; i++){
			if (word.charAt(i) == c){
				currentState.setCharAt(i, c);
			}
		}
		return currentState.toString();
	}

}
