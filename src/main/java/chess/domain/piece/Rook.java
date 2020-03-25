package chess.domain.piece;

import chess.domain.Position;

public class Rook extends Piece {
	public Rook(Position position, Team team) {
		super(position, team);
		this.representation = 'R';
	}

	@Override
	public void validateMove(Position destination) {

	}

}
