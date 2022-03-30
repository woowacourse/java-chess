package chess.domain.state;

import chess.domain.board.Rank;
import java.util.Map;

public final class Terminate extends End {

    public Terminate(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public Winner findWinner() {
        return Winner.TERMINATE;
    }
}
