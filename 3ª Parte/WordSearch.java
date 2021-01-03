import java.util.Scanner;

public class WordSearch {

    private PuzzleReader file = new PuzzleReader("Puzzle.txt");
    private Puzzle puzzle = new Puzzle(file.getPuzzle(), file.getHiddenWords());
    private String [] wordsFound = new String [puzzle.numberHiddenWords()];
    private Puzzle game;
    private int score;
    private int durationsInSeconds;
    private double currentTime;
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
        double startTime = System.currentTimeMillis();
        double currentTime = startTime;
        int count = 0;

            do{
                Move move = new Move(sc, sc, sc, sc, puzzle().rows(), puzzle().columns());
                if(play(move)){
                    wordsFound [count] = getWord(move);
                }
                count++;
            }while(!isFinished());

    }

    public Puzzle puzzle(){
        return puzzle.board();
    }

    public int duration(){
        return durationsInSeconds;
    }

    public int howManyFoundWords(){
        return wordsFound.lenght;
    }
    
    public String [] foundWords(){
        return wordsFound;
    }

    public int score(){
        return score;
    }

    public boolean isFinished(){
        return ((howManyFoundWords() == foundWords().length) || (currentTime / 1000 > startTime / 1000 + duration() ));
    }

    public boolean play(Move move){
        boolean isHidden = false;
        if (currentTime / 1000 < startTime / 1000 + duration()){
            for (String hiddenWord : puzzle.getHiddenWords()){
                if (hiddenWord.equals(getWord(move))){
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
    }

}

