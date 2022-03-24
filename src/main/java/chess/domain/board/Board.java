package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.strategy.BoardInitializeStrategy;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

public class Board {

	private final Map<Position, Piece> pieces;

	public Board(final BoardInitializeStrategy strategy) {
		pieces = new HashMap<>(strategy.createPieces());
	}

	public void move(final Position start, final Position target) {
		final Piece movingPiece = pieces.get(start);
		if (movingPiece == null) {
			throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다.");
		}
		final Direction direction = movingPiece.findValidDirection(start, target);
		Position current = start.move(direction);
		while (!current.equals(target)) {
			if (pieces.get(current) != null) {
				throw new IllegalArgumentException("다른 말이 경로에 존재해 이동할 수 없습니다.");
			}
			current = current.move(direction);
		}
		final Piece targetPiece = pieces.get(target);
		if (targetPiece == null || movingPiece.getColor() != targetPiece.getColor()) {
			pieces.put(target, movingPiece);
			pieces.remove(start);
			return;
		}
		throw new IllegalArgumentException("같은 팀의 다른 말이 존재해 이동할 수 없습니다.");
	}

	public Map<Position, Piece> getPieces() {
		return pieces;
	}
}
