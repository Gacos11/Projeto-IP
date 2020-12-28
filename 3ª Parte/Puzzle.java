public class Puzzle {
	private char [][] board;
	private String [] hiddenWords;
	private char [][] boardCopy;				//<---------------------- NAO SEI FAZER ISTO
	
	public static void main (String[] args) {
		PuzzleReader puzzle = new PuzzleReader("Puzzle.txt");
		char[][] board = puzzle.getPuzzle();
		String [] hiddenWords = puzzle.getHiddenWords();
		//isHidden(board, "");
		//System.out.println(definesPuzzle(board, hiddenWords));
	}

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

    private static boolean isHidden(char[][] board, String word){

        boolean isHidden = false;
		for (char[] row : board) {				//Checks if hiddenWord is in a line of the puzzle
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

		for (int i = 14; i < size + 14; i++){
			StringBuilder diagonal = new StringBuilder();	
			for (int j = 0; j < board.length; j++){
				for (int k = board[0].length - 1; k >= 0; k--){
					//System.out.println(i);
					//System.out.println(k);
					if (j + k == i){
						//System.out.println(j);
						//System.out.println(k);
						//System.out.println(diagonal.append(board[j][k]));
					}
				}
			}
			//System.out.println();
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
		
		if (hiddenWords.length == 0){
			isValid = false;
		}

		for (String hiddenWord : hiddenWords){
			if(!isHidden(board, hiddenWord)){
				isValid = false;
			}
			if (hiddenWord.length() < 1){
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
		this.board = board;
		this.hiddenWords = hiddenWords;
		//this.boardCopy = board;								<-------- NAO SEI SE ISSO ESTA CERTO!!!!!
	}

	public int rows(){
		return this.board.length;
	}

	public int columns(){
		return this.board[0].length;
	}

	public int numberHiddenWords(){
		return this.hiddenWords.length;
	}

	public char[][] board(){
		return this.boardCopy;						//<------------ NAO SEI FAZER
	}

	public String getWord(Move move){
		String line;
		String wordFound = "null";

		for (String hiddenWord : hiddenWords) {

			//See if the move corresponds to a word hidden in a line
			if (move.startRow() == move.endRow()) {			
				line = String.valueOf(board[move.startRow() - 1]);
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
					sb.append(board[i - 1][move.startColumn() - 1]);
				}
				String column = sb.toString();

				if (column.equals(hiddenWord)) {
					wordFound = column;
				} else if (reverseString(column).equals(hiddenWord)) {
					wordFound = reverseString(column);
				}
			}

			//See if the move corresponds to a hidden word diagonally
			else if ((move.startRow < move.endRow) && (move.startColumn < move.endColumn)){
				StringBuilder sb = new StringBuilder();
				int i = 0;
				while ((move.startRow != move.endRow) && (move.startColumn != move.endColumn)){
					sb.append(board[move.startRow - i - 1] [move.startColumn - i - 1]);
					i++;
				}
				String diagonal = sb.toString();
				if (diagonal.equals(hiddenWord)) {
					wordFound = diagonal;
				} else if (reverseString(diagonal).equals(hiddenWord)) {
					wordFound = reverseString(diagonal);
				}
			}

			//See if the move corresponds to a hidden word diagonally
			else if ((move.startRow < move.endRow) && (move.startColumn > move.endColumn)){
				StringBuilder sb = new StringBuilder();
				int i = 0;
				while ((move.startRow != move.endRow) && (move.startColumn != move.endColumn)){
					sb.append(board[move.startRow + i - 1] [move.startColumn + i - 1]);
					i++;
				}
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