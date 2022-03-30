package chess.domain.state;

import chess.domain.board.Rank;
import java.util.Map;

public final class BlackWin extends End {

    public BlackWin(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public Winner findWinner() {
        return Winner.BLACK;
    }
}
