package chess.domain.board;

public enum BlackPiecePosition {

    LEFT_KNIGHT_RANK(8),
    LEFT_KNIGHT_FILE(7),
    RIGHT_KNIGHT_RANK(8),
    RIGHT_KNIGHT_FILE(2),

    LEFT_BISHOP_RANK(8),
    LEFT_BISHOP_FILE(3),
    RIGHT_BISHOP_RANK(8),
    RIGHT_BISHOP_FILE(6),

    LEFT_ROOK_FILE(1),
    LEFT_ROOK_RANK(8),
    RIGHT_ROOK_FILE(8),
    RIGHT_ROOK_RANK(8),

    PAWN_RANK(7),

    QUEEN_RANK(8),
    QUEEN_FILE(4),

    KING_RANK(8),
    KING_FILE(5),

    ;

    private final int value;

    BlackPiecePosition(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
