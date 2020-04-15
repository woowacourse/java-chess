package chess.exception;

import chess.piece.Team;

public class InvalidTurnException extends InvalidMovementException {

    private static final String EXCEPTION_MESSAGE_FORMAT = "해당 말의 차례가 아닙니다. 지금은 %s의 차례입니다.";

    public InvalidTurnException(Team turn) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, turn.isWhite() ? "백팀" : "흑팀"));
    }
}
