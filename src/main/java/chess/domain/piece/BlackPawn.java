package chess.domain.piece;

import static chess.domain.coordinates.Direction.DOWN;
import static chess.domain.coordinates.Direction.LEFT_DOWN;
import static chess.domain.coordinates.Direction.RIGHT_DOWN;

import java.util.Arrays;
import java.util.List;

import chess.domain.coordinates.Direction;

public class BlackPawn extends Pawn {
	private static final List<Direction> BLACK_PAWN_MOVABLE_DIRECTIONS = Arrays.asList(LEFT_DOWN, RIGHT_DOWN, DOWN);

	public BlackPawn() {
		super(BLACK_PAWN_MOVABLE_DIRECTIONS, BLACK_PAWN_NAME, Color.BLACK);
	}
}
