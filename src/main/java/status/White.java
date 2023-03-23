package status;

import domain.board.Board;
import domain.position.Position;

final class White extends Turn {
    private static final String NOT_WHITE_PIECE = "흰 플레이어 차례입니다.";

    public White(final Board board) {
        super(board);
    }

    @Override
    protected void validateTurn(final Position source) {
        if (board.isBlack(source)) {
            throw new IllegalArgumentException(NOT_WHITE_PIECE);
        }
    }

    @Override
    protected Status getNextTurn() {
        return new Black(board);
    }
}
