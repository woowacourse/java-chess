package chess.domain.state;

import chess.domain.board.Board;

public final class Terminate extends End {

    public Terminate(Board board) {
        super(board);
    }

    @Override
    public Winner findWinner() {
        return Winner.TERMINATE;
    }

    @Override
    public boolean isTerminated() {
        return true;
    }

    @Override
    public StateType getType() {
        return StateType.TERMINATE;
    }
}
