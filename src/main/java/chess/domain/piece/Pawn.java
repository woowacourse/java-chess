package chess.domain.piece;

import chess.domain.Position;

public class Pawn extends Piece {
	public Pawn(Position position, Team team) {
		super(position, team);
		this.representation = 'P';
	}

	@Override
	public void move(Position destination) {

	}

}
