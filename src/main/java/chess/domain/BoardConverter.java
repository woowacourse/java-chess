package chess.domain;

import java.util.ArrayList;
import java.util.List;

import chess.domain.piece.Piece;

public class BoardConverter {
	public static String convert(ChessBoard chessBoard, String marker) {
		List<List<String>> boardInfo = makeStringBoard(chessBoard.getPieces(), marker);

		StringBuilder builder = new StringBuilder();
		for (List<String> strings : boardInfo) {
			for (String string : strings) {
				builder.append(string);
			}
		}
		return builder.toString();
	}

	public static List<List<String>> makeStringBoard(List<Piece> pieces, String marker) {
		List<List<String>> board = new ArrayList<>();
		makeEmptyBoard(marker, board);
		deployPieces(pieces, board);
		return board;
	}

	private static void deployPieces(List<Piece> pieces, List<List<String>> board) {
		for (Piece piece : pieces) {
			board.get(8 - piece.getPosition().getRow().getSymbol())
					.set(piece.getPosition().getCol().getValue() - 1, piece.getName());
		}
	}

	private static void makeEmptyBoard(String marker, List<List<String>> board) {
		for (int i = 0; i < 8; i++) {
			List<String> emptyRow = new ArrayList<>();
			for (int j = 0; j < 8; j++) {
				emptyRow.add(marker);
			}
			board.add(emptyRow);
		}
	}
}
