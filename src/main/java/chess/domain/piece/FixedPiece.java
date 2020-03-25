package chess.domain.piece;

import java.util.Set;

import chess.domain.Board;
import chess.domain.position.Position;

public abstract class FixedPiece extends AbstractPiece {
	protected FixedPiece(String name, Color color) {
		super(name, color);
	}

	@Override
	public Set<Position> canMove(Position currentPosition, Board board) {
		return null;
	}
}
