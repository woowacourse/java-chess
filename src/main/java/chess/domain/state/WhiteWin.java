package chess.domain.state;

import chess.domain.board.Board;

final class WhiteWin extends Finished {
    WhiteWin(Board board) {
        super(board);
    }
}
