public class Queens
{
	public static void main(String args[])
	{
		// Creates empty chessboard
		int[][] board = {{0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0}};
		solve(board,0,0);
		printBoard(board);
	}

	public static boolean solve(int[][] board, int y, int x)
	{
		// Base value, returns true if position finishes board and there are 8 queens
		if (y == 8 && countOnes(board) == 8) {
			return true;
		} else if (y == 8) {
			// Returns false if we don't have 8 queens, backtracks
			return false;
		}
		// Two choices for each position, has queen or doesn't have queen
		// trys queen first, no queen as fallback (no queen will always work)
		// Starts at 1 (queen), goes down to 0 (no queen)
		for (int i = 1; i >= 0; i--) {
			// checks if queen is interfering with any other queens (if i = 1) or continues (if i = 0, valid check will always return true)
			if (valid(board, y, x, i)) {
				// Sets board to option chosen
				board[y][x] = i;
				// increments y axis if position is at end of x axis, or just increments x axis
				// then attempts to solve next position in board
				// exits loop if there is no winning move, but that shouldn't happen since 0 (no queen) is always valid.
				// Should really only backtrack when there are less than 8 queens
				if (x == 7) {
					if (solve(board, y + 1, 0)) {
						return true;
					}
				} else {
					if (solve(board, y, x + 1)) {
						return true;
					}
				}
			}
		}
		board[y][x] = 0;
		return false;
	}

	public static boolean valid(int[][] board, int y, int x, int choice)
	{
		if (choice == 0) {
			return true;
		}
		// Returns false if any queens in same y axis
		for (int i = 0; i < 8; i++) {
			if (board[y][i] == 1)
				return false;
		}
		// Returns false if any queens in same x axis
		for (int i = 0; i < 8; i++) {
			if (board[i][x] == 1)
				return false;
		}
		// Returns false if any queens diagonal (left/up -> right/down)
		int tempx = x;
		int tempy = y;
		while (tempx > 0 && tempy > 0) {
			tempx--;
			tempy--;
		}
		while (tempx < 8 && tempy < 8) {
			if (board[tempy][tempx] == 1)
				return false;
			tempy++;
			tempx++;
		}

		// Returns false if any queens diagonal (left/down -> right/up)
		tempx = x;
		tempy = y;
		while (tempx > 0 && tempy < 7) {
			tempx--;
			tempy++;
		}
		while (tempx < 8 && tempy > -1) {
			if (board[tempy][tempx] == 1)
				return false;
			tempx++;
			tempy--;
		}
		return true;
	}

	// Simply counts the 1s (queens) on the board, used to make sure we have 8
	public static int countOnes(int[][] board) {
		int retval = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == 1) {
					retval++;
				}
			}
		}
		return retval;
	}

	// Prints board to screen
	public static void printBoard(int[][] board)
	{
		for (int i = 0; i < 8; i++) {
			System.out.print(" ");
			for (int j = 0; j < 8; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	// Prints a map of which spaces can take queens based on which already have queens.
	// I used this for debugging purposes
	public static void validMap(int[][] board)
	{
		for (int i = 0; i < 8; i++) {
			System.out.print(" ");
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == 1) {
					System.out.print("*");
				} else if (valid(board, i, j, 1)) {
					System.out.print("T");
				} else {
					System.out.print("F");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
