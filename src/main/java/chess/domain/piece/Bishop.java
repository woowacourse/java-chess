package chess.domain.piece;

import chess.domain.Position;

public class Bishop extends Piece {

	public Bishop(Position position, Team team) {
		super(position, team);
		this.representation = 'B';
	}

	@Override
	public void validateMove(Position destination) {

	}

}
