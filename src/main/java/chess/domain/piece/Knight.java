package chess.domain.piece;

import java.util.List;

import chess.domain.position.UnitDirection;

public final class Knight extends MovingUnitSquarePiece {
	private final static String BUG_MESSAGE_COLOR = "[BUG] 나이트는 색상을 가져야합니다.";
	private static final String BLACK_KNIGHT = "♘";
	private static final String WHITE_KNIGHT = "♞";

	private static final List<UnitDirection> MOVABLE_UNIT_DIRECTIONS = List.of(
		UnitDirection.NNE,
		UnitDirection.NNW,
		UnitDirection.SSE,
		UnitDirection.SSW,
		UnitDirection.EEN,
		UnitDirection.EES,
		UnitDirection.WWN,
		UnitDirection.WWS
	);

	Knight(Color color) {
		super(color, 2.5, MOVABLE_UNIT_DIRECTIONS);
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
	//
	// @Override
	// public boolean canMove(Direction direction, Piece target) {
	// 	checkSameTeam(target);
	// 	return direction.hasSame(MOVABLE_UNIT_DIRECTIONS);
	// }
}
