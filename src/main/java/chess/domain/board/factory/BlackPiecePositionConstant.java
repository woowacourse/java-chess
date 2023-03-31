package chess.domain.board.factory;

public enum BlackPiecePositionConstant {

    LEFT_KNIGHT_ROW(8),
    LEFT_KNIGHT_COLUMN(7),
    RIGHT_KNIGHT_ROW(8),
    RIGHT_KNIGHT_COLUMN(2),

    LEFT_BISHOP_ROW(8),
    LEFT_BISHOP_COLUMN(3),
    RIGHT_BISHOP_ROW(8),
    RIGHT_BISHOP_COLUMN(6),

    LEFT_ROOK_COLUMN(1),
    LEFT_ROOK_ROW(8),
    RIGHT_ROOK_COLUMN(8),
    RIGHT_ROOK_ROW(8),

    PAWN_ROW(7),

    QUEEN_ROW(8),
    QUEEN_COLUMN(4),

    KING_ROW(8),
    KING_COLUMN(5),

    ;

    private final int value;

    BlackPiecePositionConstant(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
