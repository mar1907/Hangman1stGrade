package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataAcquisition {
	private List<String> easy;
	private List<String> medium;
	private List<String> hard;
	
	private static final String[] hardLetters ={"x", "y", "q", "w", "k", "z"};
	private static final String[] mediumLetters ={"b","f", "g", "h","j","l","n","p","r","v"};
	
	private Scanner scanner;
	private Random rand;
	
	public DataAcquisition(String fileName) {
		try {
			scanner = new Scanner(new File (fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Well, something is wrong. Bad input file");
		}
		rand = new Random();
		easy=new ArrayList<>();
		medium=new ArrayList<>();
		hard=new ArrayList<>();
		readFile();
	}
	
	public DataAcquisition() {
		this("words.txt");
	}
	
	public Word getWord(int level){
		Word word = null;
		int item;
		switch(level){
		case 1:
			item = rand.nextInt(easy.size());
			word = new Word(easy.get(item));
			break;
		case 2:
			item = rand.nextInt(medium.size());
			word = new Word(medium.get(item));
			break;
		case 3:
			item = rand.nextInt(hard.size());
			word = new Word(hard.get(item));
			break;
		default:
			break;
		}
		return word;
	}
	
	public void printLists(){
		System.out.println(easy);
		System.out.println(medium);
		System.out.println(hard);
	}
	
	private void readFile(){
		String line; 
		boolean appended;
		while(scanner.hasNextLine()){
			appended = false;
			line = scanner.nextLine().trim().toLowerCase();
			for (String c: hardLetters){
				if (line.contains(c)) {
					hard.add(line);
					appended = true;
					break;
				}
			}
			if (!appended){
				for (String c: mediumLetters){
					if (line.contains(c)) {
						medium.add(line);
						appended = true;
						break;
					}
				}
			}
			if (!appended){
				easy.add(line);
			}
		}
	}
	
}
