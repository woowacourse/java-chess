package chess.domain.state;

import chess.domain.board.Board;

public class BlackWin extends Finished {

    BlackWin(Board board) {
        super(board);
    }
}
