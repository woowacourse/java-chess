package chess.domain.piece;

import chess.domain.Position;

public class Bishop extends Piece {

	public Bishop(Position position, Team team) {
		super(position, team);
		this.representation = 'B';
	}

	@Override
	public void validateMove(Position destination) {
		if (this.position.checkNondiagonalDirection(destination)) {
			throw new IllegalArgumentException("말이 움직일 수 없는 자리입니다.");
		}
	}

}
