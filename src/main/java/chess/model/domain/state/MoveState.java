package chess.model.domain.state;

import chess.model.domain.piece.Color;

public enum MoveState {
    SUCCESS_BUT_PAWN_PROMOTION(true, "성공하였습니다, 폰의 승격이 필요합니다."),
    SUCCESS(true, "성공하였습니다."),
    FAIL_NO_PIECE(false, "피스가 선택되지 않았습니다."),
    FAIL_NOT_ORDER(false, "현재 차례의 말이 아닙니다."),
    FAIL_CAN_NOT_MOVE(false, "해당 경로로 이동할 수 없습니다."),
    FAIL_MUST_PAWN_PROMOTION(false, "실패하였습니다, 승격되지 않은 폰이 존재합니다."),
    READY(false, "준비중"),
    KING_CAPTURED(true, "왕이 잡혔습니다."),
    NO_PAWN_PROMOTION(false, "승격이 가능하지 않습니다."),
    NEEDS_PROMOTION(false, "승격이 필요합니다."),
    EMPTY(false, ""),
    SUCCESS_PROMOTION(false, "승격이 성공했습니다.");

    private final boolean succeed;
    private final String message;

    MoveState(boolean succeed, String message) {
        this.succeed = succeed;
        this.message = message;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public Color turnTeam(Color gameTurn) {
        if (this == SUCCESS || this == SUCCESS_PROMOTION) {
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

    public String getMessage() {
        return message;
    }
}
