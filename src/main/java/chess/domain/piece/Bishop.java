package chess.domain.piece;

import static chess.domain.coordinates.Direction.LEFT_DOWN;
import static chess.domain.coordinates.Direction.LEFT_UP;
import static chess.domain.coordinates.Direction.RIGHT_DOWN;
import static chess.domain.coordinates.Direction.RIGHT_UP;

import java.util.Arrays;
import java.util.List;

import chess.domain.coordinates.Direction;

public class Bishop extends StretchablePiece {
	private static final String BISHOP_NAME = "B";
	private static final List<Direction> BISHOP_DIRECTIONS = Arrays.asList(LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN);
	private static final double BISHOP_SCORE = 3.0;

	public Bishop(Color color) {
		super(BISHOP_DIRECTIONS, BISHOP_NAME, color, BISHOP_SCORE);
	}
}
