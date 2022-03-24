package chess.domain.board.state;

import chess.domain.board.Rank;
import java.util.Map;

public final class WhiteTurn extends Playing {

    public WhiteTurn(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public boolean isBlackTurn() {
        return false;
    }
}
