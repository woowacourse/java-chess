package chess.domain.piece;

import static chess.domain.coordinates.Direction.LEFT_DOWN_DOWN;
import static chess.domain.coordinates.Direction.LEFT_LEFT_DOWN;
import static chess.domain.coordinates.Direction.LEFT_LEFT_UP;
import static chess.domain.coordinates.Direction.LEFT_UP_UP;
import static chess.domain.coordinates.Direction.RIGHT_DOWN_DOWN;
import static chess.domain.coordinates.Direction.RIGHT_RIGHT_DOWN;
import static chess.domain.coordinates.Direction.RIGHT_RIGHT_UP;
import static chess.domain.coordinates.Direction.RIGHT_UP_UP;

import java.util.Arrays;
import java.util.List;

import chess.domain.coordinates.Direction;

public class Knight extends MovablePiece {
	private static final String KNIGHT_NAME = "N";
	private static final List<Direction> KNIGHT_DIRECTIONS = Arrays.asList(LEFT_LEFT_DOWN, LEFT_LEFT_UP, LEFT_DOWN_DOWN,
			LEFT_UP_UP, RIGHT_RIGHT_DOWN, RIGHT_RIGHT_UP, RIGHT_DOWN_DOWN, RIGHT_UP_UP);
	private static final double KNIGHT_SCORE = 2.5;

	public Knight(Color color) {
		super(KNIGHT_DIRECTIONS, KNIGHT_NAME, color, KNIGHT_SCORE);
	}
}
