
public class BoggleTray {
	private class Boggled {
		private char element;
		private boolean selected;

		public Boggled(char c) {
			element = c;
			selected = false;
		}
	}

	private Boggled[][] board;
	int R;
	int C;

	// Constructor takes a 2D array of characters that represents the
	// Boggle BoggleTray with 16 dice already rolled in a known fixed state.
	public BoggleTray(char[][] array) {
		R = array.length;
		C = array[0].length;
		board = new Boggled[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (array[i][j] > 96 && array[i][j] < 123) {
					board[i][j] = new Boggled((char) (array[i][j] - 32));
				} else {
					board[i][j] = new Boggled(array[i][j]);
				}
			}
		}
	}

	public String turnQUtoQ(String str) {
		if (str.length() < 2)
			return str;
		else if (str.substring(0, 2).equals("QU"))
			return "Q" + str.substring(2);
		else
			return str.charAt(0) + turnQUtoQ(str.substring(1));
	}

	// Return true if str is found in the Boggle BoggleTray according to Boggle
	// rules.
	// Note: This method does NOT check to see if the word is in the list of
	// words.
	public boolean foundInBoggleTray(String str) {
		if (str.length() == 0)
			return false;
		String s = str.toUpperCase();
		s = turnQUtoQ(s);
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j].element == s.charAt(0)
						&& board[i][j].selected == false) {
					board[i][j].selected = true;
					if (foundInBoggleTray(0, i, j, s.substring(1)) == true) {
						board[i][j].selected = false;
						return true;
					}
					board[i][j].selected = false;
				}
			}
		}
		return false;
	}

	private boolean foundInBoggleTray(int index, int x, int y, String str) {
		if (index >= str.length())
			return true;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if ((i >= 0 && i < R) && (j >= 0 && j < C)
						&& (!(i == x && j == y))) {
					if (board[i][j].selected == false
							&& board[i][j].element == str.charAt(index)) {
						board[i][j].selected = true;
						if (foundInBoggleTray(index + 1, i, j, str) == true) {
							board[i][j].selected = false;
							return true;
						} else
							board[i][j].selected = false;
					}
				}
			}
		}
		return false;
	}

}