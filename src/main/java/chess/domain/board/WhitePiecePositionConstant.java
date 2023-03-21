package chess.domain.board;

public enum WhitePiecePositionConstant {

    LEFT_KNIGHT_ROW(1),
    LEFT_KNIGHT_COLUMN(2),
    RIGHT_KNIGHT_ROW(1),
    RIGHT_KNIGHT_COLUMN(7),

    LEFT_BISHOP_ROW(1),
    LEFT_BISHOP_COLUMN(3),
    RIGHT_BISHOP_ROW(1),
    RIGHT_BISHOP_COLUMN(6),

    LEFT_ROOK_COLUMN(1),
    LEFT_ROOK_ROW(1),
    RIGHT_ROOK_COLUMN(8),
    RIGHT_ROOK_ROW(1),

    PAWN_ROW(2),

    QUEEN_ROW(1),
    QUEEN_COLUMN(4),

    KING_ROW(1),
    KING_COLUMN(5),

    ;

    private final int value;

    WhitePiecePositionConstant(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
