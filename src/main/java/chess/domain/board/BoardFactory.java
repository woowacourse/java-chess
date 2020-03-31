package chess.domain.board;

import static chess.domain.piece.Color.*;
import static chess.domain.piece.PawnMovingStrategy.*;
import static chess.domain.piece.PieceType.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;

public class BoardFactory {
	private BoardFactory() {
	}

	public static Board create() {
		Map<Position, Piece> pieces = new HashMap<>();
		for (Color color : Color.values()) {
			initializeByColor(pieces, color);
		}
		initializePawn(pieces);
		return new Board(pieces);
	}

	private static void initializeByColor(Map<Position, Piece> pieces, Color color) {
		pieces.put(Position.of("A" + color.getInitialRow()), new Piece(color, ROOK));
		pieces.put(Position.of("B" + color.getInitialRow()), new Piece(color, KNIGHT));
		pieces.put(Position.of("C" + color.getInitialRow()), new Piece(color, BISHOP));
		pieces.put(Position.of("D" + color.getInitialRow()), new Piece(color, QUEEN));
		pieces.put(Position.of("E" + color.getInitialRow()), new Piece(color, KING));
		pieces.put(Position.of("F" + color.getInitialRow()), new Piece(color, BISHOP));
		pieces.put(Position.of("G" + color.getInitialRow()), new Piece(color, KNIGHT));
		pieces.put(Position.of("H" + color.getInitialRow()), new Piece(color, ROOK));
	}

	public static Map<Position, Piece> initializePawn(Map<Position, Piece> pieces) {
		Column.columnNames()
			.stream()
			.map(Column::getName)
			.forEach(x -> {
				pieces.put(Position.of(x + WHITE_PAWN_ROW),
					new Piece(WHITE, WHITE_PAWN));
				pieces.put(Position.of(x + BLACK_PAWN_ROW),
					new Piece(BLACK, BLACK_PAWN));
			});

		return pieces;
	}
}
