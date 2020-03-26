package chess.domain.piece;

import chess.domain.Position;
import chess.exception.IllegalMoveException;

public class Rook extends Piece {

	public static final String ILLEGAL_MOVE = "말이 움직일 수 없는 자리입니다.";

	public Rook(Position position, Team team) {
		super(position, team);
		this.representation = 'R';
		this.score = 5;
	}

	@Override
	public void validateMove(Position destination) {
		if (this.position.isNonLinearDirection(destination)) {
			throw new IllegalMoveException(ILLEGAL_MOVE);
		}
	}

}
