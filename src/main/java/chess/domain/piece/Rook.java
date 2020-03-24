package chess.domain.piece;

import chess.domain.position.Position;

public class Rook implements Piece {
	private final Color color;

	public Rook(Color color) {
		this.color = color;
	}

	@Override
	public boolean canMove(Position currentPosition, Position nextPosition) {
		if (currentPosition.equals(nextPosition)) {
			return false;
		}
		return currentPosition.isSameColumn(nextPosition) || currentPosition.isSameRow(nextPosition);
	}
}
