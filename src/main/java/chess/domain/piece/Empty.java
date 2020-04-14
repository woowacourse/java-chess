package chess.domain.piece;

import static chess.domain.piece.Team.*;

import java.util.List;

import chess.domain.position.Position;

public class Empty extends Piece {
	public static final Empty EMPTY = new Empty();

	public Empty() {
		super(NONE, ".");
	}

	@Override
	public List<Position> findMoveModeTrace(Position from, Position to) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getInitialCharacter() {
		return ".";
	}

	@Override
	public double getScore() {
		throw new UnsupportedOperationException();
	}
}
