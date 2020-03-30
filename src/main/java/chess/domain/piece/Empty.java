package chess.domain.piece;

import chess.domain.position.Position;

public class Empty extends Piece {
	public Empty(Position position, Team team) {
		super(position, Name.EMPTY, team);
	}

	@Override
	public void canPawnMove(Piece that) {
		throw new IllegalAccessError();
	}

	@Override
	protected boolean isNotMovableTo(Position start, Position destination) {
		return true;
	}
}
