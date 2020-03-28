package chess.domain.piece;

import chess.domain.Position;
import chess.exception.IllegalMoveException;

public class Rook extends Piece {

	public Rook(Position position, Team team) {
		super(position, team);
		this.representation = 'R';
		this.score = 5;
	}

	@Override
	protected void validateMove(Position destination) {
		if (this.position.isNonLinearDirection(destination)) {
			throw new IllegalMoveException(ILLEGAL_MOVE);
		}
	}
}
