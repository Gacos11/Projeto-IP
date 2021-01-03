public class WordSearch {

    private PuzzleReader file = new PuzzleReader("Puzzle.txt");
    private Puzzle puzzle = new Puzzle(file.getPuzzle(), file.getHiddenWords());
    private String [] wordsFound = new String [puzzle.numberHiddenWords()];
    private Puzzle game;
    private int wordPoints;
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
        int gameDuration = puzzle.duration();
        this.durationsInSeconds = durationsInSeconds;
        double meanTime = durationsInSeconds / puzzle.numberHiddenWords();

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
        return wordPoints;
    }

    public boolean isFinished(){
        return ((howManyFoundWords() == foundWords().length));
    }

    public boolean play(Move move){
        boolean isHidden = false;
        if(currentTime < duration()){
            for (String hiddenWord : puzzle.getHiddenWords()){
                if (hiddenWord.equals(getWord(move))){
                    isHidden = true;
                }
            }
        }
        return isHidden;
    }

    public String toString(){
    }

}

