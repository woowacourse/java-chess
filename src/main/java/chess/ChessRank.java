package chess;

import java.util.HashMap;
import java.util.Map;

public class ChessRank {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 8;
    private static final Map<Integer, ChessRank> CHESS_RANKS = new HashMap<>();

    private final int chessRank;

    static {
        for (int i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
            CHESS_RANKS.put(i, new ChessRank(i));
        }
    }

    private ChessRank(int chessRank) {
        validate(chessRank);
        this.chessRank = chessRank;
    }

    public static ChessRank from(int chessRank) {
        return CHESS_RANKS.getOrDefault(chessRank, new ChessRank(chessRank));
    }

    private void validate(int chessRank) {
        if (chessRank < LOWER_BOUND || chessRank > UPPER_BOUND) {
            throw new IllegalArgumentException("유효한 체스 랭크가 아닙니다.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(chessRank);
    }
}
