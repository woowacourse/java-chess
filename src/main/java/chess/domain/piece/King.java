package chess.domain.piece;

import chess.domain.Position;

public class King extends Piece {
	public King(Position position, Team team) {
		super(position, team);
		this.representation = 'K';
	}

	@Override
	public void move(Position destination) {

	}
}
