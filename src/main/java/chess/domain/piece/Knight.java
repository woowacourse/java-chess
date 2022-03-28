package chess.domain.piece;

import java.util.List;

import chess.domain.position.Direction;

public final class Knight extends Piece {
	private final static String BUG_MESSAGE_COLOR = "[BUG] 나이트는 색상을 가져야합니다.";
	private static final String BLACK_KNIGHT = "♘";
	private static final String WHITE_KNIGHT = "♞";
	private static final List<Direction> MOVABLE_DIRECTIONS = List.of(
		new Direction(1, 2),
		new Direction(1, -2),
		new Direction(-1, 2),
		new Direction(-1, -2),
		new Direction(2, 1),
		new Direction(2, -1),
		new Direction(-2, -1),
		new Direction(-2, 1)
	);

	Knight(Color color) {
		super(color, 2.5);
	}

	@Override
	public String getEmoji() {
		if (color == Color.NONE) {
			throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
		}

		if (color == Color.BLACK) {
			return BLACK_KNIGHT;
		}

		return WHITE_KNIGHT;
	}

	@Override
	public boolean canMove(Direction direction, Piece target) {
		checkSameTeam(target);
		return direction.hasSame(MOVABLE_DIRECTIONS);
	}
}
