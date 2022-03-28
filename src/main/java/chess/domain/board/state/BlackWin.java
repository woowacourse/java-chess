package chess.domain.board.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import java.util.Map;

public final class BlackWin extends End {

    public BlackWin(Board board) {
        super(board);
    }

    @Override
    public Winner findWinner() {
        return Winner.BLACK;
    }
}
