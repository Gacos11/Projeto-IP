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

	private final int [] pos1 = new int[1];
	private final int [] pos2 = new int[1];
	private final int [] size = new int[1];
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
		this.pos1[0] = row1;
		this.pos1[1] = col1;
		this.pos2[0] = row2;
		this.pos2[1] = col2;
		this.size[0] = rows;
		this.size[1] = columns;
	}
	
	public int [] startRow(){
		return this.pos1;
	}

	public int [] endRow(){
		return this.pos2;
	}

	public Direction direction(){
		return direction;
	}

	public int [] rows(){
		return this.size;
	}
}