package chess.domain.state;

import chess.domain.board.Board;

public final class BlackTurn extends Playing {

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public boolean isBlackTurn() {
        return true;
    }

    @Override
    public StateType getType() {
        return StateType.BLACK_TURN;
    }
}
