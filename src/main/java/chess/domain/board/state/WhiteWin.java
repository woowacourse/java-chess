package chess.domain.board.state;

import chess.domain.board.Rank;
import java.util.Map;

public final class WhiteWin extends End {

    public WhiteWin(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public boolean isBlackWin() {
        return false;
    }
}
