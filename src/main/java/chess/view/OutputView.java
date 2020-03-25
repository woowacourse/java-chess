package chess.view;

import java.util.Map;

public class OutputView {
	public static void printBoard(Map<String, String> board) {
		for (int i = 8; i >= 1; i--) {
			for (char c = 'a'; c <= 'h'; c++) {
				String key = String.valueOf(c) + i;
				if (board.containsKey(key)) {
					System.out.print(board.get(key));
					continue;
				}
				System.out.print(".");
			}
			System.out.println();
		}
	}
}
