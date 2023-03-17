package chess.exception;

public enum PieceMessage {

    BISHOP_INVALID_MOVE("Bishop의 이동 범위가 올바르지 않습니다."),
    KING_INVALID_MOVE("King의 이동 범위가 올바르지 않습니다."),
    KNIGHT_INVALID_MOVE("Knight의 이동 범위가 올바르지 않습니다."),
    PAWN_INVALID_MOVE("Pawn의 이동 범위가 올바르지 않습니다."),
    PLACE_INVALID_MOVE("Place는 움직일 수 없습니다."),
    QUEEN_INVALID_MOVE("Queen의 이동 범위가 올바르지 않습니다."),
    ROOK_INVALID_MOVE("Rook의 이동 범위가 올바르지 않습니다.")
    ;

    private final String message;

    PieceMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
