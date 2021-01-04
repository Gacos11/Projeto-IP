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

	private int [] positions = new int [4];
	private int rows;
	private int columns;
	
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
			this.positions [0] = row1;
			this.positions [1] = col1;
			this.positions [2] = row2;
			this.positions [3] = col2;
	}
	
	public int startRow(){
		return positions[0];
	}
	
	public int startColumn() {
		return positions[1];
	}

	public int endRow(){
		return positions[2];
	}

	public int endColumn(){
		return positions[3];
	}

	public Direction direction(){
		Direction direction;
		if ((positions[0] == positions[2]) && (positions[1] != positions[3])){
			direction = Direction.HORIZONTAL;
		}
		else if ((positions[0] != positions[1]) && (positions[1] == positions[3])){
			direction = Direction.VERTICAL;
		}
		else if ((positions[0] < positions[2]) && (positions[1] < positions[3])){
			direction = Direction.DIAGONAL_RIGHT;
		}
		else {
			direction = Direction.DIAGONAL_LEFT;
		}
		return direction;
	}

	public int rows(){
		return this.rows;
	}

	public int columns(){
		return this.columns;
	}
}