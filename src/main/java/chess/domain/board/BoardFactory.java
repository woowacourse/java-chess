package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.coordinates.Column;
import chess.domain.coordinates.Coordinates;
import chess.domain.coordinates.Row;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;

public class BoardFactory {
	private static final Map<Coordinates, Piece> INITIAL_STATE_PIECES = initializePieces();

	private BoardFactory() {
	}

	public static Board createNewGame() {
		return new Board(new HashMap<>(INITIAL_STATE_PIECES));
	}

	private static Map<Coordinates, Piece> initializePieces() {
		Map<Coordinates, Piece> pieces = new HashMap<>();
		initialize(pieces, "1", Color.WHITE);
		initialize(pieces, "8", Color.BLACK);
		initializePawn(pieces, Row.of("2"), new WhitePawn());
		initializePawn(pieces, Row.of("7"), new BlackPawn());
		return pieces;
	}

	private static void initialize(Map<Coordinates, Piece> pieces, String rowName, Color color) {
		pieces.put(Coordinates.of("A" + rowName), new Rook(color));
		pieces.put(Coordinates.of("B" + rowName), new Knight(color));
		pieces.put(Coordinates.of("C" + rowName), new Bishop(color));
		pieces.put(Coordinates.of("D" + rowName), new Queen(color));
		pieces.put(Coordinates.of("E" + rowName), new King(color));
		pieces.put(Coordinates.of("F" + rowName), new Bishop(color));
		pieces.put(Coordinates.of("G" + rowName), new Knight(color));
		pieces.put(Coordinates.of("H" + rowName), new Rook(color));
	}

	private static void initializePawn(Map<Coordinates, Piece> pieces, Row row, Piece pawn) {
		Column.values()
				.stream()
				.map(column -> Coordinates.of(column, row))
				.forEach(coordinates -> pieces.put(coordinates, pawn));
	}
}
