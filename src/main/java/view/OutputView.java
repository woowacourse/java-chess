package view;

import java.util.Map;

import chess.board.ChessBoard;
import chess.board.Location;
import chess.piece.Piece;

public class OutputView {
	public static void printBoard(ChessBoard chessBoard) {
		Map<Location, Piece> board = chessBoard.getBoard();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Location target = new Location(8 - i, (char)('a' + j));
				String value = findPieceOrDefault(board, target);
				System.out.print(value);
			}
			System.out.println();
		}
	}

	private static String findPieceOrDefault(Map<Location, Piece> board, Location target) {
		String value = ".";
		if (board.containsKey(target)) {
			Piece piece = board.get(target);
			value = piece.toString();
		}
		return value;
	}
}
