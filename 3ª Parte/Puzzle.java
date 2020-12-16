public class Puzzle {

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
        
        for (int i = 0; i < board[0].length; i++){
            for (int j = 0; j < board[j].length; j++){
                
            }
        }
		return isHidden;
    }
}