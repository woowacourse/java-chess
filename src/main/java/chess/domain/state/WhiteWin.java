package chess.domain.state;

import chess.domain.board.Rank;
import java.util.Map;

public final class WhiteWin extends End {

    public WhiteWin(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public Winner findWinner() {
        return Winner.WHITE;
    }
}
