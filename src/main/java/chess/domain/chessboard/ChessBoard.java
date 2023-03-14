package chess.domain.chessboard;

import java.util.ArrayList;
import java.util.List;

public final class ChessBoard {

    private static final int FIXED_RANK_SIZE = 8;

    private final List<Rank> ranks = new ArrayList<>();

    public ChessBoard(){
        initRanks();
    }

    private void initRanks() {
        for (int rankIndex = 0; rankIndex < FIXED_RANK_SIZE; rankIndex++) {
            ranks.add(new Rank());
        }
    }
}
