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
    public String getState() {
        return "black_win";
    }

    @Override
    public String getStateName() {
        return "흑팀 승리";
    }
}
