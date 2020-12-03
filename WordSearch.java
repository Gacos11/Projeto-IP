/**
* Compile:     javac NumberSearch.java
* Run:         java NumberSearch
*
*@author Duarte Ferreira, fc54981
*@author Vasco Barros, fc54986
*/

public class WordSearch {

    public static boolean isHidden (char[][] board, String word){

        boolean isHidden = false;
        for (int lin=0; lin<board.length; lin++){

            String line = new String(board[lin]);
            int intIndex = line.indexOf(word);
            if (intIndex != -1){
                isHidden = true;
            }

            for (int col=0; col<board[lin].length; col++){

            }
        }

        return isHidden;
    }
}