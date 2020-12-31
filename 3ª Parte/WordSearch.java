public class WordSearch {

    private PuzzleReader puzzle = new PuzzleReader(args[0]);
    private int wordPoints;
    private char [][] wordsFound;
    private Puzzle puzzle;
    private int durationsInSeconds;
    
    public WordSearch (Puzzle puzzle, int durationsInSeconds){
        this.puzzle = puzzle;
        this.durationsInSeconds = durationsInSeconds;
    }

    public Puzzle puzzle(){
        return this.puzzle;
    }

    public int duration(){
        int maxDuration = 600;
        return maxDuration;
    }

    public int howManyFoundWords(){
        return this.wordsFound.lenght;
    }
    
    public string [] foundWords(){
    }

    public int score(){
        return this.wordPoints;
    }

    public boolean isFinished(){
        return (howManyFoundWords() == foundWords().length);
    }

    public boolean play(Move move){
    }

    public String toString(){
    }
}

