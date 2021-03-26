package chess.domain.game.state;

import chess.domain.board.Board;

public class WhiteWin extends Finished {

    public WhiteWin(Board board) {
        super(board);
    }

    @Override
    public String winner() {
        return "백색";
    }
}
