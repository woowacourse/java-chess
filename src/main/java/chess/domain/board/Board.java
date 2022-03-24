package chess.domain.board;

import java.util.Map;

import chess.domain.board.strategy.BoardInitializeStrategy;
import chess.domain.piece.Piece;

public class Board {

	private final Map<Position, Piece> pieces;

	public Board(final BoardInitializeStrategy strategy) {
		pieces = strategy.createPieces();
	}

	public Map<Position, Piece> getPieces() {
		return pieces;
	}
}
