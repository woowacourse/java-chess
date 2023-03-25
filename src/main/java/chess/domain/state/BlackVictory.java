package chess.domain.state;

import chess.domain.Board;

public class BlackVictory extends Finished{
    protected BlackVictory(final Board board) {
        super(board);
    }
}
