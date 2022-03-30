package chess.domain.piece;

import java.util.List;

import chess.domain.position.UnitDirection;

public final class King extends MovingUnitSquarePiece {
	private final static String BUG_MESSAGE_COLOR = "[BUG] 킹은 색상을 가져야합니다.";
	private static final String WHITE_KING = "♚";
	private static final String BLACK_KING = "♔";

	private static final List<UnitDirection> MOVABLE_UNIT_DIRECTIONS = List.of(
		UnitDirection.NORTH,
		UnitDirection.SOUTH,
		UnitDirection.EAST,
		UnitDirection.WEST,
		UnitDirection.NORTH_EAST,
		UnitDirection.NORTH_WEST,
		UnitDirection.SOUTH_EAST,
		UnitDirection.SOUTH_WEST
	);

	King(Color color) {
		super(color, 0, MOVABLE_UNIT_DIRECTIONS);
	}

	@Override
	public String getEmoji() {
		if (color == Color.NONE) {
			throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
		}

		if (color == Color.BLACK) {
			return BLACK_KING;
		}

		return WHITE_KING;
	}

	// @Override
	// public boolean canMove(Direction direction, Piece target) {
	// 	checkSameTeam(target);
	// 	return direction.hasSame(MOVABLE_UNIT_DIRECTIONS);
	// }

	@Override
	public boolean isKing() {
		return true;
	}
}
