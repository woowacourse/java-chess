package state;

import domain.board.Board;
import domain.position.Position;

public final class White extends Playing {
    private static final String NOT_WHITE_PIECE = "흰 플레이어 차례입니다.";

    public White(final Board board) {
        super(board);
    }

    @Override
    protected void validateTurn(final Position source) {
        if (board.isBlackPiece(source)) {
            throw new IllegalArgumentException(NOT_WHITE_PIECE);
        }
    }

    @Override
    protected State getNextTurn() {
        return new Black(board);
    }
}
