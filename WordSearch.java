import java.util.Scanner;

/**
* Compile:     javac WordSearch.java
* Run:         java WordSearch
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
						wordFound = findWord(board, readMove(sc, board.length, board[0].length), hiddenWords);
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
        		System.out.println("Error: One or more of the hidden words isn't present in the puzzle.");
        	}
        }
        else {
        	System.out.println(puzzle.getErrorMsgs());
        }
    }  

    private static String reverseString(String word){
        
        String reversedWord = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            reversedWord = reversedWord + word.charAt(i);
        }
        return reversedWord;
    }

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

    public static boolean isValidGame (char[][] board, String[] hiddenWords){
        
        boolean isValidGame = true;
        
        for (int i = 0; i<hiddenWords.length; i++){
            if (!isHidden(board, hiddenWords[i])){
                isValidGame = false;
            }
        }
        return isValidGame;
    }
    
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