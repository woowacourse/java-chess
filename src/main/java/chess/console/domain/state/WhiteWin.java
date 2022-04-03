package chess.console.domain.state;

import chess.console.domain.board.Board;

public class WhiteWin extends Finished {

    WhiteWin(Board board) {
        super(board);
    }
}
