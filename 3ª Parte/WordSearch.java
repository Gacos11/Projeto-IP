import java.util.Scanner;
import java.lang.System;

public class WordSearch {

    private PuzzleReader file = new PuzzleReader("Puzzle.txt");
    private Puzzle puzzle = new Puzzle(file.getPuzzle(), file.getHiddenWords());
    private String [] hiddenWords = file.getHiddenWords();
    private String [] wordsFound = new String [puzzle.numberHiddenWords()];
    //private StringBuilder [] wordsFound = new StringBuilder [puzzle.numberHiddenWords()];
    private Puzzle game;
    private int score;
    private int durationsInSeconds;
    private double currentTime;
    private int startTime = (int) System.currentTimeMillis();
    private static String reverseString(String word) {

		String reversedWord = "";

		for (int i = word.length() - 1; i >= 0; i--) {
			reversedWord = reversedWord + word.charAt(i);
		}
		return reversedWord;
    }
    
    public WordSearch (Puzzle puzzle, int durationsInSeconds){

        Scanner sc = new Scanner(System.in);
        this.durationsInSeconds = durationsInSeconds;
        int gameDuration = duration();
        double meanTime = durationsInSeconds / puzzle.numberHiddenWords();
        int wordPoints = puzzle().rows() * puzzle().columns() / 10;
        int currentTime = startTime;
        int count = 0;
        int [] playersMove = new int[4];

            do{
                
                Move move = new Move(playersMove[0] , playersMove[1], playersMove[2], playersMove[3], puzzle().rows(), puzzle().columns());
                if(play(move)){
                    currentTime = currentTime + (int) System.currentTimeMillis();
                    if (currentTime >= meanTime){
                        score = score + wordPoints;
                    }
                    else{
                        score = score + ((int) (1 + meanTime - currentTime) * wordPoints);
                    }
                    wordsFound [count] = puzzle.getWord(move);
                }
                count++;
            }while(!isFinished());

    }

    public Puzzle puzzle(){
        return puzzle;
    }

    public int duration(){
        return durationsInSeconds;
    }

    public int howManyFoundWords(){
        int count = 0;
        for (String wordFound : wordsFound){
            if (wordFound != null){
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
        return score;
    }

    public boolean isFinished(){
        return ((howManyFoundWords() == puzzle.numberHiddenWords()) || (currentTime / 1000 > startTime / 1000 + duration() ));
    }

    public boolean play(Move move){
        boolean isHidden = false;
        if (currentTime / 1000 < startTime / 1000 + duration()){
            for (String hiddenWord : hiddenWords){
                if (hiddenWord.equals(puzzle.getWord(move))){
                    isHidden = true;
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

