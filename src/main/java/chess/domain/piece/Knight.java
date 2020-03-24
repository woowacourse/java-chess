package chess.domain.piece;

import chess.domain.Position;

public class Knight extends Piece {
	public Knight(Position position, Team team) {
		super(position, team);
		this.representation = 'N';
	}

	@Override
	public void move(Position destination) {

	}

}
