/**
* Compile:     javac WordSearch.java
* Run:         java WordSearch
*
*@author Duarte Ferreira, fc54981
*@author Vasco Barros, fc54986
*/


public class WordSearch {

    public static void main (String [] args){
        PuzzleReader puzzle = new PuzzleReader(args[0]);
        char [][] board = puzzle.getPuzzle();
        String[] hiddenWords = puzzle.getHiddenWords();
        System.out.println(isValidGame(board, hiddenWords));
        
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
        for (int i = 0; i<board[0].length; i++){
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

    public static boolean isValidGame(char[][] board, String[] hiddenWords){
        
        boolean isValidGame = true;
        
        for (int i = 0; i<hiddenWords.length; i++){
            if (!isHidden(board, hiddenWords[i])){
                isValidGame = false;
            }
        }
        return isValidGame;
    }
}
