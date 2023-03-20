package chess.domain.board;

public enum WhitePiecePosition {

    LEFT_KNIGHT_RANK(1),
    LEFT_KNIGHT_FILE(2),
    RIGHT_KNIGHT_RANK(1),
    RIGHT_KNIGHT_FILE(7),

    LEFT_BISHOP_RANK(1),
    LEFT_BISHOP_FILE(3),
    RIGHT_BISHOP_RANK(1),
    RIGHT_BISHOP_FILE(6),

    LEFT_ROOK_FILE(1),
    LEFT_ROOK_RANK(1),
    RIGHT_ROOK_FILE(8),
    RIGHT_ROOK_RANK(1),

    PAWN_RANK(2),

    QUEEN_RANK(1),
    QUEEN_FILE(4),

    KING_RANK(1),
    KING_FILE(5),

    ;

    private final int value;

    WhitePiecePosition(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
