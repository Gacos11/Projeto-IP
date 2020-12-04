/**
* Compile:     javac WordSearch.java
* Run:         java WordSearch
*
*@author Duarte Ferreira, fc54981
*@author Vasco Barros, fc54986
*/

public class WordSearch {

    public static void main (String [] args){
        System.out.println(puzz)
        System.out.println(isHidden())
    }

    public static boolean isHidden (char[][] board, String word){

        boolean isHidden = false;
        for (int lin=0; lin<board.length; lin++){

            String line = new String(board[lin]);
            int intIndex = line.indexOf(word);
            
            if (intIndex != -1){
                isHidden = true;
            }

            for (int col=0; col<board[lin].length; col++){
                StringBuilder column = new StringBuilder(board[col][lin]);
                intIndex = column.indexOf(word);

                if(intIndex != -1){
                    isHidden = true;
                }
            }
        }

        return isHidden;
    }

    public static boolean isValidGame (char[][] board, String[] hiddenWords){

    }
}