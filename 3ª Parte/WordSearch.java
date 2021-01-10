/**
 * The {@code WordSearch} class defines a set of procedures that recreate a
 * simple version of the game.
 * 
 * @author Duarte Ferreira, fc54981
 * @author Vasco Barros, fc54986
 */

import java.util.Scanner;
import java.lang.System;

public class WordSearch {

    private Puzzle puzzle;
    private String [] hiddenWords;
    private String [] wordsFound;
    private int score = 0;
    private int durationsInSeconds;
    private long startTime = System.currentTimeMillis();
    private long timeOfTheLastWordFound;
    private long currentTime = startTime;
    private int meanTime;
    private int wordPoints;

    private static String reverseString(String word) {

		String reversedWord = "";

		for (int i = word.length() - 1; i >= 0; i--) {
			reversedWord = reversedWord + word.charAt(i);
		}
		return reversedWord;
    }
    
    public WordSearch (Puzzle puzzle, int durationsInSeconds){
        this.puzzle = puzzle;
        hiddenWords = puzzle.getHiddenWords();
        wordsFound = new String [puzzle.numberHiddenWords()];
        this.durationsInSeconds = durationsInSeconds;
        int gameDuration = duration();
        meanTime = durationsInSeconds / puzzle.numberHiddenWords();
        wordPoints = puzzle().rows() * puzzle().columns() / 10;
    }

    public Puzzle puzzle(){
        return puzzle;
    }

    public int duration(){
        return durationsInSeconds;
    }

    public int howManyFoundWords(){
        int count = 0;
        for (int i = 0; i < wordsFound.length; i++){
            if (wordsFound[i] != null){
                count++;
            }
        }
        return count;
    }
    
    public String [] foundWords(){
        String [] foundWords = new String [howManyFoundWords()];
        for (int i = 0; i < howManyFoundWords(); i++){
                foundWords [i] = wordsFound [i];
        }
        return foundWords;
    }

    public int score(){
        currentTime = System.currentTimeMillis();
        if (howManyFoundWords() == 1){
            if ((int)currentTime/1000 - (int)startTime/1000 >= meanTime){
                score = score + wordPoints;
                timeOfTheLastWordFound = currentTime;
            }
            else{
                score = score + (1 + meanTime - ((int)currentTime/1000 - (int)startTime/1000))*wordPoints;
                timeOfTheLastWordFound = currentTime;
            }
        }
        else{
            if ((int)currentTime/1000 - (int)timeOfTheLastWordFound/1000 >= meanTime){
                score = score + wordPoints;
                timeOfTheLastWordFound = currentTime;
            }
            else{
                score = score + (1 + meanTime - ((int)currentTime/1000 - (int)timeOfTheLastWordFound/1000))*wordPoints;
                timeOfTheLastWordFound = currentTime;
            }
        }
        return score;
    }

    public boolean isFinished(){
        return ((howManyFoundWords() == puzzle.numberHiddenWords()) || ((int)currentTime/1000 > (int)startTime/1000 
        + duration()));
    }

    public boolean play(Move move){
        boolean isHidden = false;
        currentTime = System.currentTimeMillis();
        if ((int)currentTime/1000 < (int)startTime/1000 + duration()){
            for (String hiddenWord : hiddenWords){
                if (puzzle.getWord(move) != null){
                    if (hiddenWord.equals(puzzle.getWord(move))){
                        isHidden = true;
                        for (int i = 0; i < wordsFound.length; i++){
                            
                            if ((wordsFound[i] == null)){
                                wordsFound[i] = puzzle.getWord(move);
                                return isHidden;
                            }
                            else if (wordsFound[i].equals(puzzle.getWord(move))){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        else {
            isFinished();
        }
        return isHidden;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for (String hiddenWord : hiddenWords){
            result.append(hiddenWord.toString()+ " ");
        }
        return result.toString();
    }

}

