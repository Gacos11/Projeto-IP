public class Puzzle{
  //private char[][] b;
//  private String[] hd;
public static void main(String[] args){
  PuzzleReader puzzle = new PuzzleReader("Puzzle.txt");
    char [][] board = puzzle.getPuzzle();
    String[] hiddenWords  = puzzle.getHiddenWords();
  System.out.println(definesPuzzle(board,hiddenWords));
}
private static String reversed(String word){
  StringBuilder rev = new StringBuilder();
  rev.append(word);
  rev = rev.reverse();
  String rever = rev.toString();
  return rever;
}
  public static boolean isHidden(char[][] board, String word){
    boolean isHidden = false;
    for(int li=0; li<board.length; li++){
      String line = new String(board[li]);
      boolean nor = line.contains(word);
      boolean rev = line.contains(reversed(word));
      if( nor || rev ){
      isHidden = true;
      }

    for(int i = 0; i<board[0].length;i++){
      StringBuilder column = new StringBuilder();
      for(int col=0; col< board.length; col++){
        column.append(board[col][i]);
        String column1 = column.toString();
        boolean nor1 = column1.contains(word);
        boolean rev1 = column1.contains(reversed(word));
        if(nor1 || rev1){
          isHidden = true;
        }
      }
    }
  }
  return isHidden;
}
  public static boolean definesPuzzle(char[][] board, String[] hiddenWords){
    boolean definesPuzzle = false;
    if(hiddenWords.length > 0 ){
      for(int i =0; i< hiddenWords.length; i++){
          if(hiddenWords[i].length() >= 2){
          definesPuzzle = true;
          }
          else{
            definesPuzzle = false;
            return definesPuzzle;
          }
          if(isHidden(board,hiddenWords[i])){
              definesPuzzle =true;
          }
          else{
            definesPuzzle = false;
            return definesPuzzle;
          }
        for(int j= i+1; j< hiddenWords.length; j++){
          if(hiddenWords[i] != hiddenWords[j]){
          definesPuzzle = true;
          } else{
          definesPuzzle = false;
          return definesPuzzle;
          }
        }

      }
    }
    return definesPuzzle;
  }
}