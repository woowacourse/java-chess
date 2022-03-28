package chess.domain.board.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import java.util.Map;

public final class WhiteTurn extends Playing {

    public WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public boolean isBlackTurn() {
        return false;
    }
}
