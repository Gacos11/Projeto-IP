public class WordSearch {

    private int wordPoints;
    private char [][] wordsFound;
    
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
    }

    public boolean play(Move move){
    }

    public String toString(){
    }
}

