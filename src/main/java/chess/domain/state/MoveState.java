package chess.domain.state;

public enum MoveState {
    SUCCESS_BUT_PAWN_CHANGE(true),
    SUCCESS(true),
    FAIL_NO_PIECE(false),
    FAIL_NOT_ORDER(false),
    FAIL_CAN_NOT_MOVE(false),
    FAIL_MUST_PAWN_CHANGE(false),
    KING_CAPTURED(false);

    private final boolean success;

    MoveState(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
