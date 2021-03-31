package chess.domain.game.state;

import chess.domain.board.Board;

public class BlackWin extends Finished {

    public BlackWin(Board board) {
        super(board);
    }

    @Override
    public String winner() {
        return "흑색";
    }

    @Override
    public String state() {
        return "BlackWin";
    }
}
