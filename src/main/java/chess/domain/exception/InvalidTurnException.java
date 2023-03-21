package chess.domain.exception;

import chess.domain.piece.Color;

public class InvalidTurnException extends IllegalStateException {

    public InvalidTurnException(final Color currentTurnColor) {
        super(currentTurnColor.getValue() + "의 차례입니다.");
    }
}
