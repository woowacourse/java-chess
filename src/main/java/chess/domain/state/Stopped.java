package chess.domain.state;

import chess.domain.board.Board;

public class Stopped extends Finished {

    Stopped(Board board) {
        super(board);
    }
}

