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
    public String getState() {
        return "white_win";
    }

    @Override
    public String getStateName() {
        return "백팀 승리";
    }
}
