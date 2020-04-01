package chess.domain.piece;

import static chess.domain.coordinates.Direction.LEFT_UP;
import static chess.domain.coordinates.Direction.RIGHT_UP;
import static chess.domain.coordinates.Direction.UP;

import java.util.Arrays;
import java.util.List;

import chess.domain.coordinates.Direction;

public class WhitePawn extends Pawn {
	private static final List<Direction> WHITE_PAWN_MOVABLE_DIRECTIONS = Arrays.asList(LEFT_UP, RIGHT_UP, UP);

	public WhitePawn() {
		super(WHITE_PAWN_MOVABLE_DIRECTIONS, WHITE_PAWN_NAME, Color.WHITE);
	}
}
