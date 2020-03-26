package chess.domain.piece;

import chess.domain.Position;
import chess.exception.IllegalMoveException;

public class Queen extends Piece {

	public Queen(Position position, Team team) {
		super(position, team);
		this.representation = 'Q';
		this.score = 9;
	}

	@Override
	public void validateMove(Position destination) {
		if (this.position.isNonDiagonalDirection(destination) && this.position.isNonLinearDirection(
			destination)) {
			throw new IllegalMoveException(ILLEGAL_MOVE);
		}
	}
}
