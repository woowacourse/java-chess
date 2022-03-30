package chess.domain.state;

import chess.domain.board.Board;

public class WhiteWin extends Finished {

    WhiteWin(Board board) {
        super(board);
    }
}
