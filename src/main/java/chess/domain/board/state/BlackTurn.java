package chess.domain.board.state;

import chess.domain.board.Rank;
import java.util.Map;

public final class BlackTurn extends Playing {

    public BlackTurn(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public boolean isBlackTurn() {
        return true;
    }
}
