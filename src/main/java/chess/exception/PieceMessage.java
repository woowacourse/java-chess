package chess.exception;

public enum PieceMessage {

    BISHOP_INVALID_MOVE("Bishop의 이동 범위가 올바르지 않습니다."),
    KING_INVALID_MOVE("King의 이동 범위가 올바르지 않습니다."),
    KNIGHT_INVALID_MOVE("Knight의 이동 범위가 올바르지 않습니다."),
    PAWN_INVALID_MOVE("Pawn의 이동 범위가 올바르지 않습니다."),
    PLACE_INVALID_MOVE("Place는 움직일 수 없습니다."),
    QUEEN_INVALID_MOVE("Queen의 이동 범위가 올바르지 않습니다."),
    ROOK_INVALID_MOVE("Rook의 이동 범위가 올바르지 않습니다."),
    MOVE_NOT_TO_TEAM("우리팀 말에게 이동할 수 없습니다."),
    MOVE_NOT_TO_OBSTACLE("이동경로에 장애물이 존재합니다."),
    NOT_MOVE("같은 위치로 움직일 수 없습니다."),
    ONLY_MOVE_MINE("상대편 말은 움직일 수 없습니다.");

    private final String message;

    PieceMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
