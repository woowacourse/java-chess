package chess.domain.state;

import chess.domain.board.Board;

public final class WhiteWin extends End {

    public WhiteWin(Board board) {
        super(board);
    }

    @Override
    public Winner findWinner() {
        return Winner.WHITE;
    }

    @Override
    public StateType getType() {
        return StateType.WHITE_WIN;
    }
}
