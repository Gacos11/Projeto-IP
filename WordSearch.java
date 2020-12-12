import java.util.Scanner;

/**
* The {@code WordSearch} class defines a set of procedures 
* that recreate a simple version of the game.
* 
* The program can be tested using a *******       <---------------------------
* given as an argument when running the class.
* 
* Compile:     javac WordSearch.java
* Run:         java WordSearch Puzzle.txt
*
*@author Duarte Ferreira, fc54981
*@author Vasco Barros, fc54986
*/

public class WordSearch {

    public static void main (String [] args){
    	
    	Scanner sc = new Scanner(System.in);
    	PuzzleReader puzzle = new PuzzleReader(args[0]);
    	boolean isPuzzleAvailable = puzzle.isPuzzleAvailable();
        
        if (isPuzzleAvailable) {
        	char [][] board = puzzle.getPuzzle();
        	String[] hiddenWords = puzzle.getHiddenWords();
        	
        	if (isValidGame(board, hiddenWords)) {
        		
        		printPuzzle(board, hiddenWords);
        		int notFinished = hiddenWords.length;
				String wordFound; 
				String wordsFound = "";
        		
        		do {
        			do {
        				
        				System.out.println();
						wordFound = findWord(board, readMove(sc, board.length, board[0].length), 
						hiddenWords);
						if (wordFound.contains("null")){
							System.out.println("The move doesn't correspond to a hidden word.");
						}
        				
        			}while(wordFound.contains("null"));
        			
        			wordsFound = wordsFound + wordFound + " ";
        			notFinished = notFinished - 1;
        			System.out.println("Found words: " +wordsFound);
        			System.out.println("Hidden words: " +notFinished);
        			
        		}while(notFinished != 0);
        		
        		System.out.println();
        		System.out.println("Good work. All hidden words were found.");
        	}
        	else {
				System.out.println("Error: One or more of the hidden words isn't present in the 
				puzzle.");
        	}
        }
        else {
        	System.out.println(puzzle.getErrorMsgs());
        }
    }  
    
    /**
     * Reverses a given String.
     * 
     * @param word the string to be reversed
     * @requires {code word is a string}
     * @ensures to reverse every character of the given string
     * @return string This returns the string, but reversed
     */
    private static String reverseString(String word){
        
        String reversedWord = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            reversedWord = reversedWord + word.charAt(i);
        }
        return reversedWord;
    }
    
    /**
     * Determinates wether a word is hidden in the puzzle
     * 
     * @param board The puzzle we are using, consists of an array
     * @param word the String that we are checking if it's present in the Board
     * @requires {code word != null && board is an array}
     * @ensures the boolean is false if the word isn't hidden in the board and true if it is hidden
     * @return boolean Returns true of false
     */
    public static boolean isHidden (char[][] board, String word){
        
        boolean isHidden = false;
        for (char[] row : board) {
            if (new String(row).contains(word) || new String(row).contains(reverseString(word))) {
                isHidden = true;
            }
        }
        
        for (int i = 0; i<board[i].length; i++){
            StringBuilder column = new StringBuilder();
            for (int j = 0; j<board.length; j++){
                column.append(board[j][i]);
                String columnString = new String(column);
                if (columnString.contains(word) || columnString.contains(reverseString(word))){
                    isHidden = true;
                }
            }
        }
        return isHidden;
    }
    
    /**
     * Determinates wether a game is valid or not
     * 
     * @param board The puzzle we are using, consists of an array
     * @param hiddenWords the string wich contains all the hidden words in the game
     * @requires {code hiddenWords != null && board is an array}
     * @ensures the boolean is false if the game isn't valid and true if it is valid
     * @return boolean Returns true or false
     */
    public static boolean isValidGame (char[][] board, String[] hiddenWords){
        
        boolean isValidGame = true;
        
        for (int i = 0; i<hiddenWords.length; i++){
            if (!isHidden(board, hiddenWords[i])){
                isValidGame = false;
            }
        }
        return isValidGame;
    }
    
    /**
     * Determinates wether a move is valid or not
     * 
     * @param move ---------
     * @param rows --------
     * @param columns ---------
     * @requires {code move != null && rows > 0 && columns > 0}
     * @ensures the boolean is false if the move isn't valid and tru if it is valid
     * @return boolean Returns true or false
     */
    public static boolean isValidMove (int[] move, int rows, int columns) {
    	
    	boolean isValid = true;
    	
    	if (move.length != 4) {
    		isValid = false;
    	}
    	
    	if (move[0] < 0 || move[0] > rows || move[0] > move[2]) {
    		isValid = false;
    		}
    	
    	if (move[1] < 0 || move[1] > columns || move[1] > move[3]) {
    		isValid = false;
    	}
    	
    	if (move[2] < 0 || move[2] > rows) {
    		isValid = false;
    	}
    	
    	if (move[3] < 0 || move[3] > columns) {
    		isValid = false;
    	}
    	
    	return isValid;
    }
    
    /**
     * 
     * 
     * @param sc
     * @param rows
     * @param columns
     * @requires {code sc != null && rows > 0 && columns > 0} 
     * @ensures
     * @return
     */
    public static int [] readMove(Scanner sc, int rows, int columns) {
    	
    	boolean isValid = false;
    	int [] move = new int[4];
    	
    	do {
    		System.out.print("Give your move: ");
    		
    		for (int i = 0; i < 4; i++) {
    			move[i] = sc.nextInt();
    		}
    		
    		if (isValidMove(move, rows, columns)) {
    			isValid = true;
    		}
    		
    		else {
    			System.out.println("Your move is invalid.");
    			System.out.println();
    		}
    		
    	}while(!isValid);
    	
    	return move;
    }
    
    /**
     * Determines wether a hidden word was found by the move
     * 
     * @param board The puzzle we are using, consists of an array
     * @param move -------
     * @param hiddenWords the string wich contains all the hidden words in the game
     * @requires {code board is an array &&  isValidGame(char[][] board, String[] hiddenWords) &&
	 * isValidMove(move, board.length, board[0].length) }
     * @ensures either the word found of null, if no words were foun
     * @return string Returns null or the word found
     */
    public static String findWord (char[][] board, int [] move, String[] hiddenWords) {
    	
    	String line;
    	String wordFound = "null";
    	
    	for (String hiddenWord : hiddenWords) {

    		if (move[0] == move [2]) {
    			line = String.valueOf(board[move[0] - 1]);
    			line = line.substring(move[1] - 1, move[3]);
    			
    			if (line.equals(hiddenWord)) {
    				wordFound = line;
    			}
    			else if (reverseString(line).equals(hiddenWord)) {
    				wordFound = reverseString(line);
    			}
    		}
    	
    		else if (move[1] == move [3]){
    			
    			StringBuilder sb = new StringBuilder();
    			
    			for (int i = move[0]; i <= move[2]; i++) {
    				sb.append(board[i - 1] [move[1] - 1]);
    			}
    			String column = sb.toString();
    			
    			if (column.equals(hiddenWord)) {
    				wordFound = column;
    			}
    			else if (reverseString(column).equals(hiddenWord)) {
    				wordFound = reverseString(column);
    			}
    		}
    	}
    	return wordFound;
    }
    
    /**
     * Prints the whole puzzle and the number of words hidden within it
     * 
     * @param @param board The puzzle we are using, consists of an array
     *  @param hiddenWords the string wich contains all the hidden words in the game
     * @requires {code board is an array && hiddenWords != null}
     */
    public static void printPuzzle (char[][] board, String [] hiddenWords) {
    	
    	System.out.print("   ");
    	
    	for (int i = 1; i <= board[0].length; i++) {
    		if (i < 10) {
    			System.out.print(i + "  ");
    		}
    		else {
    			System.out.print(i + " ");
    		}
    	}
    	
    	System.out.println();
    	
    	for (int i = 0; i<board.length; i++){
    		
    		if (i < 9) {
    			System.out.print((i + 1) + "  ");
    		}
    		else {
    			System.out.print((i + 1) + " ");
    		}
            
    		for (int j = 0; j<board[i].length; j++){
            	System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    	System.out.println();
    	System.out.println("Hidden words: " +hiddenWords.length);
    }
}