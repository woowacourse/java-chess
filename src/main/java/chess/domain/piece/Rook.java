package chess.domain.piece;

import chess.domain.Position;

public class Rook extends Piece {

	public Rook(Position position, Team team) {
		super(position, team);
		this.representation = 'R';
	}

	@Override
	public void validateMove(Position destination) {
		if (this.position.checkLinearDirection(destination)) {
			throw new IllegalArgumentException("말이 움직일 수 없는 자리입니다.");
		}
	}

}
