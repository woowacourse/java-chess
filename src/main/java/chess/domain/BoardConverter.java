package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;

public class BoardConverter {
	public static final String BLANK_MARK = ".";

	public static ChessBoard convertToBoard(String board) {
		List<Piece> pieces = new ArrayList<>();
		int col = 0;
		int row = 8;

		for (String pieceName : board.split("")) {
			Optional<Piece> piece = PieceFactory.of(pieceName.toLowerCase(), convertToSide(pieceName),
					new Position((col % 8) + 1, row));

			if (piece.isPresent()) {
				pieces.add(piece.orElseThrow(NoSuchElementException::new));
			}
			if (++col % 8 == 0) {
				row--;
			}
		}
		return new ChessBoard(pieces);
	}

	private static Side convertToSide(String boardName) {
		if (Character.isUpperCase(boardName.charAt(0))) {
			return Side.BLACK;
		}
		return Side.WHITE;
	}

	public static String convertToString(ChessBoard chessBoard) {
		List<List<String>> boardInfo = makeStringBoard(chessBoard.getPieces());

		StringBuilder builder = new StringBuilder();
		for (List<String> strings : boardInfo) {
			for (String string : strings) {
				builder.append(string);
			}
		}
		return builder.toString();
	}

	public static List<List<String>> makeStringBoard(List<Piece> pieces) {
		List<List<String>> board = new ArrayList<>();
		makeEmptyBoard(board);
		deployPieces(pieces, board);
		return board;
	}

	private static void deployPieces(List<Piece> pieces, List<List<String>> board) {
		for (Piece piece : pieces) {
			board.get(8 - piece.getPosition().getRow().getSymbol())
					.set(piece.getPosition().getCol().getValue() - 1, piece.getName());
		}
	}

	private static void makeEmptyBoard(List<List<String>> board) {
		for (int i = 0; i < 8; i++) {
			List<String> emptyRow = new ArrayList<>();
			for (int j = 0; j < 8; j++) {
				emptyRow.add(BLANK_MARK);
			}
			board.add(emptyRow);
		}
	}
}
