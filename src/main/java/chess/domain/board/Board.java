package chess.domain.board;

import static chess.domain.piece.Piece.*;

import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Board {
	private final Map<Position, Piece> pieces;

	public Board(Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public boolean isNotEmptyPosition(Position position) {
		return pieces.get(position) != null;
	}

	public Piece findPieceBy(Position position) {
		return pieces.get(position);
	}

	public void movePiece(Position from, Position to) {
		Piece target = pieces.remove(from);

		pieces.put(to, target);
	}

	public Map<Position, Piece> getPieces() {
		return pieces;
	}

	public boolean isKingAliveOf(Color color) {
		return pieces.values().stream()
			.anyMatch(piece -> isKingOf(color, piece));
	}

	private boolean isKingOf(Color color, Piece piece) {
		return piece.isSameColor(color) && piece.getScore() == KING_SCORE;
	}
}
