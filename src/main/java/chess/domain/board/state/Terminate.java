package chess.domain.board.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import java.util.Map;

public final class Terminate extends End {

    public Terminate(Board board) {
        super(board);
    }

    @Override
    public Winner findWinner() {
        return Winner.TERMINATE;
    }
}
