package chess.domain.piece;

import java.util.Arrays;

import chess.domain.Position;
import chess.exception.IllegalMoveException;

public class Bishop extends Piece {
	public Bishop(Position position, Team team) {
		super(position, team);
		this.representation = Arrays.asList('♗', '♝');
		this.score = Rule.BISHOP_SCORE;
	}

	@Override
	public void validateMove(Position destination) {
		if (this.position.isNonDiagonalDirection(destination)) {
			throw new IllegalMoveException(ILLEGAL_MOVE);
		}
	}
}
