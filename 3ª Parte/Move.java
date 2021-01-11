/**
 * The {@code Move} class defines a set of procedures that recreate a
 * simple version of the game.
 * 
 * @author Duarte Ferreira, fc54981
 * @author Vasco Barros, fc54986
 */

public class Move{

	private int [] positions = new int [4];
	private int rows;
	private int columns;
	
	/**
	 * Defines wether ro1, col1, ro2, col2, are columns and rows from the board and form
	 * a continuous line
	 * 
	 * @param row1 a row from the board
	 * @param col1 a column from the board
	 * @param row2 a row from the board
	 * @param col2 a column from the board
	 * @param rows the number of rows in the board
	 * @param columns the number of columns in the board
	 * @ensures the boolean is true if the rows and columns form a continuous line and the boolean 
	 * is false if the rows and columns do not form a continuous line
	 * @return boolean Returns true or false
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

		if (row1 == row2 && col1 == col2){
			isValid = false;
		}
		return isValid;
	}
	
	/**
	 * Creates a move with the given data
	 * 
	 * @param row1 a row from the board
	 * @param col1 a column from the board
	 * @param row2 a row from the board
	 * @param col2 a column from the board
	 * @param rows the number of rows in the board
	 * @param columns the number of columns in the board
	 * @requires {@code definesMove(row1, col1, row2, col2, rows, columns) is true}
	 * @ensures to create a move with the given data
	 */
	public Move(int row1, int col1, int row2, int col2, int rows, int columns) {
			this.positions [0] = row1;
			this.positions [1] = col1;
			this.positions [2] = row2;
			this.positions [3] = col2;
			this.rows = rows;
			this.columns = columns;
	}

	/**
	 * Gives the starting row of the move
	 * 
	 * @ensures the integer returns is equal to the number of the row
	 * @return int Returns the starting row of the move
	 */
	public int startRow(){
		return positions[0];
	}
	
	/**
	 * Gives the starting column of the move 
	 * 
	 * @ensures the integer returns is equal to the number of the column
	 * @return int Returns the starting column of the move
	 */
	public int startColumn() {
		return positions[1];
	}

	/**
	 * Gives the ending row of the move
	 * 
	 * @ensures the integer returns is equal to the number of the row
	 * @return int Returns the ending row of the move
	 */
	public int endRow(){
		return positions[2];
	}

	/**
	 * Gives the ending column of the move
	 * 
	 * @ensures the integer returns is equal to the number of the column
	 * @return int Returns the ending column of the move
	 */
	public int endColumn(){
		return positions[3];
	}

	/**
	 * Determines the direction of the move
	 * 
	 * @return the direction of the move
	 */
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
	
	/**
	 * Gives the number of rows
	 *
	 * @ensures the integer returned is equal to the number of rows
	 * @return int Returns the number of rows
	 */
	public int rows(){
		return rows;
	}

	/**
	 * Gives the number of columns
	 *
	 * @ensures the integer returned is equal to the number of columns
	 * @return int Returns the number of columns
	 */
	public int columns(){
		return columns;
	}
}