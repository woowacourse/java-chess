package chess.domain.piece;

import static chess.domain.coordinates.Direction.DOWN;
import static chess.domain.coordinates.Direction.LEFT;
import static chess.domain.coordinates.Direction.RIGHT;
import static chess.domain.coordinates.Direction.UP;

import java.util.Arrays;
import java.util.List;

import chess.domain.coordinates.Direction;

public class Rook extends StretchablePiece {
	private static final String ROOK_NAME = "R";
	private static final List<Direction> ROOK_MOVABLE_DIRECTIONS = Arrays.asList(UP, DOWN, LEFT, RIGHT);
	private static final double ROOK_SCORE = 5.0;

	public Rook(Color color) {
		super(ROOK_MOVABLE_DIRECTIONS, ROOK_NAME, color, ROOK_SCORE);
	}
}
