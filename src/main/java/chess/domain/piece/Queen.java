package chess.domain.piece;

import chess.domain.Position;

public class Queen extends Piece {
	public Queen(Position position, Team team) {
		super(position, team);
		this.representation = 'Q';
	}

	@Override
	public void validateMove(Position destination) {
		if (this.position.checkNondiagonalDirection(destination) && this.position.checkNonlinearDirection(
			destination)) {
			throw new IllegalArgumentException("말이 움직일 수 없는 자리입니다.");
		}
	}

}
