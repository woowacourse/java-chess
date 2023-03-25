package state;

import domain.board.Board;
import domain.position.Position;

final class Black extends Playing {
    private static final String NOT_BLACK_PIECE = "검정 플레이어 차례입니다.";

    public Black(final Board board) {
        super(board);
    }

    @Override
    protected void validateTurn(final Position source) {
        if (!board.isBlackPiece(source)) {
            throw new IllegalArgumentException(NOT_BLACK_PIECE);
        }
    }

    @Override
    protected State getNextTurn() {
        return new White(board);
    }
}
