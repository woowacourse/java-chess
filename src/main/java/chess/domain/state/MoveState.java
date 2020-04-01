package chess.domain.state;

import chess.domain.piece.Color;

public enum MoveState {
    SUCCESS_BUT_PAWN_CHANGE(true),
    SUCCESS(true),
    FAIL_NO_PIECE(false),
    FAIL_NOT_ORDER(false),
    FAIL_CAN_NOT_MOVE(false),
    FAIL_MUST_PAWN_CHANGE(false),
    READY(false),
    KING_CAPTURED(false);

    private final boolean succeed;

    MoveState(boolean succeed) {
        this.succeed = succeed;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public Color turnTeam(Color gameTurn) {
        if (this == SUCCESS) {
            return gameTurn.nextTurnIfEmptyMySelf();
        }
        return gameTurn;
    }

    public boolean isReady() {
        return this == READY;
    }

    public boolean isSuccess() {
        return this == SUCCESS;
    }
}
