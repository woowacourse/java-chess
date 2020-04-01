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

public class King extends MovablePiece {
	private static final String KING_NAME = "K";
	private static final List<Direction> KING_DIRECTIONS = Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_UP, RIGHT_UP,
			LEFT_DOWN, RIGHT_DOWN);
	private static final double KING_SCORE = 0.0;

	public King(Color color) {
		super(KING_DIRECTIONS, KING_NAME, color, KING_SCORE);
	}

	@Override
	public boolean isKing() {
		return true;
	}
}
