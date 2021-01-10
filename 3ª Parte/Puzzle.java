/**
 * The {@code Puzzle} class defines a set of procedures that recreate a
 * simple version of the game.
 *
 * @author Duarte Ferreira, fc54981
 * @author Vasco Barros, fc54986
 */

public class Puzzle {

	private char [][] puzzle;
	private String [] hiddenWords;

    /**
	 * Reverses a given String.
	 * 
	 * @param word the string to be reversed
	 * @requires {@code word is a string}
	 * @ensures to reverse every character of the given string
	 * @return string This returns the string, but reversed
	 */
	private static String reverseString(String word) {

		String reversedWord = "";

		for (int i = word.length() - 1; i >= 0; i--) {
			reversedWord = reversedWord + word.charAt(i);
		}
		return reversedWord;
	}

	
    private static boolean isHidden(char [][] board, String word){

		char [][]  reversedBoard = board;
		boolean isHidden = false;

		//Checks if hiddenWord is in a line of the puzzle
		for (char[] row : board) {				
			if (new String(row).contains(word) || new String(row).contains(reverseString(word))) {
				isHidden = true;
			}
		}

		//Checks if hiddenWord is in a column of the puzzle
		for (int i = 0; i < board[i].length; i++) {
			StringBuilder column = new StringBuilder();

			for (char[] element : board) {
				column.append(element[i]);
				String columnString = new String(column);

				if (columnString.contains(word) || columnString.contains(reverseString(word))) {
					isHidden = true;
				}
			}
        }

		int size = board.length + board[0].length - 1;
		
		for (int i = 0; i < size ; i++){
		StringBuilder diagonal = new StringBuilder();	
			for (int j = 0; j < board.length; j++){
				for (int k = 0; k < board[0].length; k++){
					if (j + k == i){
						diagonal.append(board[j][k]);
					}
				}
			}
			String diagonalString = new String(diagonal);
			if (diagonalString.contains(word) || diagonalString.contains(reverseString(word))){
				isHidden = true;
			}
			diagonal.delete(0, diagonal.length());
		}

		char [][] boardCopy = new char [board.length][board[0].length];
		for (int line = 0; line < board.length; line++){
			for (int column = 0; column < board[0].length; column++){
				boardCopy [line][column] = board[line][column];
			}
		}
		for(int i = 0; i < boardCopy.length / 2; i++){
			char[] temp = boardCopy[i];
			boardCopy[i] = boardCopy[boardCopy.length - i - 1];
			boardCopy[boardCopy.length - i - 1] = temp;
		}

		for (int i = 0; i < size ; i++){
			StringBuilder diagonal = new StringBuilder();	
				for (int j = 0; j < boardCopy.length; j++){
					for (int k = 0; k < boardCopy[0].length; k++){
						if (j + k == i){
							diagonal.append(boardCopy[j][k]);
						}
					}
				}
				String diagonalString = new String(diagonal);
				if (diagonalString.contains(word) || diagonalString.contains(reverseString(word))){
					isHidden = true;
				}
				diagonal.delete(0, diagonal.length());
			}

		return isHidden;
    }
    
    public static boolean definesPuzzle(char[][] board, String[] hiddenWords) {
    	
    	boolean isValid = true;

		if (!isHidden(board, hiddenWords[1])){
			isValid = false;
		}

		for (String hiddenWord : hiddenWords){
			if(!isHidden(board, hiddenWord)){
				isValid = false;
			}
			if (hiddenWord.length() < 2){	
				isValid = false;
			}
		}
		
    	for (int i = 0; i < hiddenWords.length; i++) {
    		for (int j = i + 1; j < hiddenWords.length; j++){
				if (hiddenWords[i].equals(hiddenWords[j])){
					isValid = false;
				}
			}
		}
		return isValid;
	}
	
	public Puzzle (char [][] board, String [] hiddenWords){
		this.puzzle = board;
		this.hiddenWords = hiddenWords;
	}

	public int rows(){
		return puzzle.length;
	}

	public int columns(){
		return puzzle[0].length;
	}

	public int numberHiddenWords(){
		return hiddenWords.length;
	}

	public char[][] board(){
		char [][] boardCopy = new char [puzzle.length][puzzle[0].length];
		for (int line = 0; line < puzzle.length; line++){
			for (int column = 0; column < puzzle[0].length; column++){
				boardCopy [line][column] = puzzle[line][column];
			}
		}
		return boardCopy;
	}
	public String [] getHiddenWords(){
		return hiddenWords;
	}

	public String getWord(Move move){
		String line;
		String wordFound = null;

		for (String hiddenWord : hiddenWords) {
			//See if the move corresponds to a word hidden in a line
			if (move.startRow() == move.endRow()) {
				line = String.valueOf(puzzle[move.startRow() - 1]);
				line = line.substring(move.startColumn() - 1, move.endColumn());
				if (line.equals(hiddenWord)) {
					wordFound = line;
				} else if (reverseString(line).equals(hiddenWord)) {
					wordFound = reverseString(line);
				}
			}

			//See if the move corresponds to a word hidden in a column
			else if (move.startColumn() == move.endColumn()) {	

				StringBuilder sb = new StringBuilder();

				for (int i = move.startRow(); i <= move.endRow(); i++) {
					sb.append(puzzle[i - 1][move.startColumn() - 1]);
				}
				String column = sb.toString();

				if (column.equals(hiddenWord)) {
					wordFound = column;
				} else if (reverseString(column).equals(hiddenWord)) {
					wordFound = reverseString(column);
				}
			}

			//See if the move corresponds to a hidden word diagonally
			else if ((move.startRow() < move.endRow()) && (move.startColumn() < move.endColumn())){
				StringBuilder sb = new StringBuilder();
				int i = 0;
				int r1 = move.startRow(); 
				int r2 = move.endRow(); 
				int c1 = move.startColumn(); 
				int c2 = move.endColumn();
				do{
					sb.append(puzzle[move.startRow() + i -1] [move.startColumn() + i -1]);
					r1++;
					c1++;
					i++;
				}while ((r1 != r2 + 1) && (c1 != c2 + 1));
				String diagonal = sb.toString();
				if (diagonal.equals(hiddenWord)) {
					wordFound = diagonal;
				} else if (reverseString(diagonal).equals(hiddenWord)) {
					wordFound = reverseString(diagonal);
				}
			}

			//See if the move corresponds to a hidden word diagonally
			else if ((move.startRow() < move.endRow()) && (move.startColumn() > move.endColumn())){
				StringBuilder sb = new StringBuilder();
				int i = 0;
				int r1 = move.startRow(); 
				int r2 = move.endRow(); 
				int c1 = move.startColumn(); 
				int c2 = move.endColumn();
				do{
					sb.append(puzzle[move.startRow() - i -1] [move.startColumn() - i -1]);
					r1++;
					c1++;
					i++;
				}while ((r1 != r2 + 1) && (c1 != c2 + 1));
				String diagonal = sb.toString();
				if (diagonal.equals(hiddenWord)) {
					wordFound = diagonal;
				} else if (reverseString(diagonal).equals(hiddenWord)) {
					wordFound = reverseString(diagonal);
				}
			} 
		}
		return wordFound;
	}
}