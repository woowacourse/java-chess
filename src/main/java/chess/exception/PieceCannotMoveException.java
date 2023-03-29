package chess.exception;

import chess.domain.piece.PieceType;

public class PieceCannotMoveException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "이 움직일 수 없는 경로입니다.";

    public PieceCannotMoveException(PieceType type) {
        super(type.name() + DEFAULT_MESSAGE);
    }
}
