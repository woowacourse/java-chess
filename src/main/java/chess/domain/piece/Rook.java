package chess.domain.piece;

import java.util.List;

import chess.domain.position.UnitDirection;

public final class Rook extends MovingMultipleSquarePiece {
	private static final String BUG_MESSAGE_COLOR = "[BUG] 룩은 색상을 가져야합니다.";
	private static final String WHITE_ROOK = "♜";
	private static final String BLACK_ROOK = "♖";

	private static final List<UnitDirection> MOVABLE_UNIT_DIRECTIONS = List.of(
		UnitDirection.NORTH,
		UnitDirection.WEST,
		UnitDirection.EAST,
		UnitDirection.SOUTH
	);

	Rook(Color color) {
		super(color, 5, MOVABLE_UNIT_DIRECTIONS);
	}

	@Override
	public String getEmoji() {
		if (color == Color.NONE) {
			throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
		}

		if (color == Color.BLACK) {
			return BLACK_ROOK;
		}

		return WHITE_ROOK;
	}
}
