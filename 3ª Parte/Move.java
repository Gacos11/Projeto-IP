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

	private int r1;
	private int r2;
	private int c1;
	private int c2;
	private int lines;
	private int cols;
	
	public static void main(String[] args) {
		System.out.println(definesMove(1, 3, 10, 3, 11, 11));
	}
	
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
		this.r1 = row1;
		this.c1 = col1;
		this.r2 = row2;
		this.c2 = col2;
		this.lines = rows;
		this.cols = columns;
	}
	
	public int startRow(){
		return this.r1;
	}
	
	public int startColumn() {
		return this.c1;
	}

	public int endRow(){
		return this.r2;
	}

	public int endColumn(){
		return this.c2;
	}

	public Direction direction(){
		return direction;
	}

	public int rows(){
		return this.lines;
	}

	public int columns(){
		return this.cols;
	}
}