package chess.domain.piece;

import static chess.domain.coordinates.Direction.DOWN;
import static chess.domain.coordinates.Direction.LEFT;
import static chess.domain.coordinates.Direction.LEFT_DOWN;
import static chess.domain.coordinates.Direction.LEFT_UP;
import static chess.domain.coordinates.Direction.RIGHT;
import static chess.domain.coordinates.Direction.RIGHT_DOWN;
import static chess.domain.coordinates.Direction.RIGHT_UP;
import static chess.domain.coordinates.Direction.UP;

import java.util.Arrays;
import java.util.List;

import chess.domain.coordinates.Direction;

public class Queen extends StretchablePiece {
	private static final String QUEEN_NAME = "Q";
	private static final List<Direction> QUEEN_MOVABLE_DIRECTIONS = Arrays.asList(UP, DOWN, LEFT, RIGHT, LEFT_UP,
			LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
	private static final double QUEEN_SCORE = 9.0;

	public Queen(Color color) {
		super(QUEEN_MOVABLE_DIRECTIONS, QUEEN_NAME, color, QUEEN_SCORE);
	}
}
