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

    /**
     * Reverses a given string
     * 
     * @ensures the string returned is the same as the given string but the order of the letters is
     * reversed
     * @return string Returns a the given string in reverse
     */
    private static String reverseString(String word) {

		String reversedWord = "";

		for (int i = word.length() - 1; i >= 0; i--) {
			reversedWord = reversedWord + word.charAt(i);
		}
		return reversedWord;
    }
    
    /**
     * Creates a game with the given data
     * 
     * @param puzzle a puzzle used in the game
     * @param durationInSeconds the amount of secconds registered for the game 
     * @requires {@code puzzle != null && duartionInSeconds > 0 && durationInSeconds / ´
     * puzzle.numberHiddenWords() > 5}
     */
    public WordSearch (Puzzle puzzle, int durationsInSeconds){
        this.puzzle = puzzle;
        hiddenWords = puzzle.getHiddenWords();
        wordsFound = new String [puzzle.numberHiddenWords()];
        this.durationsInSeconds = durationsInSeconds;
        int gameDuration = duration();
        meanTime = durationsInSeconds / puzzle.numberHiddenWords();
        wordPoints = puzzle().rows() * puzzle().columns() / 10;
    }

    /**
     * Gives the puzzle of the game
     * 
     * @ensures the puzzle returned matches that of the game
     * @return puzzle Returns a puzzle of the game
     */
    public Puzzle puzzle(){
        return puzzle;
    }

    /**
     * Gives the maximum duration of the game, in seconds
     * 
     * @ensures the integer returned is the maximim amount of seconds of the game
     * @return int Returns the maximum amount of seconds
     */
    public int duration(){
        return durationsInSeconds;
    }

    /**
     * Gives the number of hidden words that have been found
     * 
     * @ensures the integer returned is equal to the amount of words found
     * @return in The integer returned is equal to the amount of words found
     */
    public int howManyFoundWords(){
        int count = 0;
        for (int i = 0; i < wordsFound.length; i++){
            if (wordsFound[i] != null){
                count++;
            }
        }
        return count;
    }
    
    /**
     * Gives a --- with the words found
     * 
     * @ensures the ----returned has the words that have been found
     * @return --- The --- returned has the hidden words that have been found
     */
    public String [] foundWords(){
        String [] foundWords = new String [howManyFoundWords()];
        for (int i = 0; i < howManyFoundWords(); i++){
                foundWords [i] = wordsFound [i];
        }
        return foundWords;
    }

    /**
     * Gives the current score
     * 
     * @ensures the integer returned is the same as the current score
     * @return in The integer returned is the same as the score
     */
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

    /**
     * Checks wether the game is finished or not
     * 
     * @ensures the boolean is true if the game is finished and false if it is not
     * @return boolena Returns true of false
     */
    public boolean isFinished(){
        return ((howManyFoundWords() == puzzle.numberHiddenWords()) || ((int)currentTime/1000 > (int)startTime/1000 
        + duration()));
    }

    /**
     * Checks whether the play is possible or not
     * 
     * @requires {@code move != null && move.rows() == puzzle().rows() && move.columns() == 
     * puzzle().columns() && !isFinished}
     * @ensures the boolean returned is false if there is no more time left, if there are no words
     * left to find the game is finished
     * @return boolean Returns true or false
     */
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

    /**
     * Gives a text representation of the match
     * 
     * @ensures 
     * @return string Returns the current state of the match
     */
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (String hiddenWord : hiddenWords){
            result.append(hiddenWord.toString()+ " ");
        }
        return result.toString();
    }

}