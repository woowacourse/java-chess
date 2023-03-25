package chess.domain.state;

import chess.domain.Board;

public class End extends Finished {
    protected End(final Board board) {
        super(board);
    }
}
