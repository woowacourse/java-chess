package chess.domain.state;

import chess.domain.Board;

public class WhiteVictory extends Finished{
    protected WhiteVictory(final Board board) {
        super(board);
    }
}
