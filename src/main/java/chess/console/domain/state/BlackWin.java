package chess.console.domain.state;

import chess.console.domain.board.Board;

public class BlackWin extends Finished {

    BlackWin(Board board) {
        super(board);
    }
}
