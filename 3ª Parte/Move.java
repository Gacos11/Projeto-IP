/**
 * The {@code Move} class defines a set of procedures that recreate a
 * simple version of the game.
 * 
 * Compile: javac Move.java 
 * Run: java Move
 *
 * @author Duarte Ferreira, fc54981
 * @author Vasco Barros, fc54986
 */

public class Move{

	private int [] pos1 = new int [2];
	private int [] pos2 = new int [2];
	
	/**
	 * @param row1
	 * @param col1
	 * @param row2
	 * @param col2
	 * @param rows
	 * @param columns
	 */
	public static boolean definesMove (int row1, int col1, int row2, int col2, int rows,
			int columns) {
		
		boolean isValid = true;
		
		if (row1 > rows || row2 > rows || col1 > columns || col2 > columns) {
			isValid = false;
		}
		
		if (row1 > row2 || col1 > col2) {
			isValid = false;
		}
		return isValid;
	}
	
	public Move(int row1, int col1, int row2, int col2, int rows, int columns) {
			this.pos1 [0] = row1;
			this.pos1 [1] = col1;
			this.pos2 [0] = row2;
			this.pos2 [1] = col2;
	}
	
	public int startRow(){
		return pos1[0];
	}
	
	public int startColumn() {
		return pos1[1];
	}

	public int endRow(){
		return pos2[0];
	}

	public int endColumn(){
		return pos2[1];
	}

	public Direction direction(){
	}

	public int rows(){
		return rows;
	}

	public int columns(){
		return columns;
	}
}