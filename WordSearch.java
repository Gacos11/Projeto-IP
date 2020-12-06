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
        char [][] puzzle1 = puzzle.getPuzzle();
        System.out.println(isHidden(puzzle1, "PROGRAMACAO"));
        /* for (int i=0; i<puzzle1.length; i++){
            System.out.println(puzzle1[i]);
            for (int j=0; j<puzzle1[i].length; j++){
                System.out.println(puzzle1[i][j]);
            }
        } */
    }  

    public static boolean isHidden (char[][] board, String word){

        
        boolean isHidden = false;
        //System.out.println(board[0].length);
        for (int lin=0; lin<board.length; lin++){

            String line = String.valueOf(board[lin]);
            int intIndex = line.indexOf(word);
            
            if (intIndex != -1){
                isHidden = true;
            }


            for (int col=0; col<board[lin].length; col++){
                StringBuilder column = new StringBuilder(board[lin][col]);
                intIndex = column.indexOf(word);

                if(intIndex != -1){
                    isHidden = true;
                }
            }
        }

        return isHidden;
    }

    /* public static boolean isValidGame(char[][] board, String[] hiddenWords){

    } */
}
