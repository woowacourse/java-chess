package chess.domain.factory;

public enum BoardIndex {
	BLACK_TEAM_EXECUTIVE_INDEX(8),
	BLACK_TEAM_PAWN_INDEX(7),
	BLACK_TO_INDEX(6),
	BLACK_FROM_INDEX(3),
	WHITE_TEAM_PAWN_INDEX(2),
	WHITE_TEAM_EXECUTIVE_INDEX(1),
	BOARD_FROM_INDEX(1),
	BOARD_TO_INDEX(8),
	ROOK_FIRST_INDEX(1),
	KNIGHT_FIRST_INDEX(2),
	BISHOP_FIRST_INDEX(3),
	QUEEN_INDEX(4),
	KING_INDEX(5),
	BISHOP_SECOND_INDEX(6),
	KNIGHT_SECOND_INDEX(7),
	ROOK_SECOND_INDEX(8),
	ROW_FROM_INDEX('a'),
	ROW_TO_INDEX('h'),
	ALL_PIECE_NUMBER(64);

	private final int index;

	BoardIndex(int index) {
		this.index = index;
	}

	public int get() {
		return index;
	}
}
