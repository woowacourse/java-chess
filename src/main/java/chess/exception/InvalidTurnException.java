package chess.exception;

import chess.domain.TeamColor;

public class InvalidTurnException extends ChessException {

    private static final String MESSAGE_FORM = "현재 턴은 %s 의 차례입니다.";

    public InvalidTurnException(TeamColor teamColor) {
        super(String.format(MESSAGE_FORM, teamColor));
    }
}
