package chess.domain.state;

import chess.domain.board.Board;

public final class BlackWin extends End {

    public BlackWin(Board board) {
        super(board);
    }

    @Override
    public Winner findWinner() {
        return Winner.BLACK;
    }

    @Override
    public boolean isBlackWin() {
        return true;
    }

    @Override
    public StateType getType() {
        return StateType.BLACK_WIN;
    }
}
