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
}
