package chess.domain.board.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import java.util.Map;

public final class WhiteWin extends End {

    public WhiteWin(Board board) {
        super(board);
    }

    @Override
    public Winner findWinner() {
        return Winner.WHITE;
    }
}
