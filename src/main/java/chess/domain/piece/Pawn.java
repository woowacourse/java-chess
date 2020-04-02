package chess.domain.piece;

import java.util.List;

import chess.domain.coordinates.Coordinates;
import chess.domain.coordinates.Direction;

public abstract class Pawn extends MovablePiece {
	public static final String BLACK_PAWN_NAME = "P";
	public static final String WHITE_PAWN_NAME = "p";
	private static final double PAWN_SCORE = 1.0;

	protected Pawn(List<Direction> movableDirections, String name, Color color) {
		super(movableDirections, name, color, PAWN_SCORE);
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public List<Coordinates> findMovableCoordinates(Coordinates from, Coordinates to) {
		return super.findMovableCoordinates(from, to);
	}
}
