package domain.board;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int RANK_ROW_COUNT = 8;
    public static final int TOTAL_COL_COUNT = 8;
    private final List<Rank> ranks;

    public Board() {
        ranks = new ArrayList<>();
        for (int row = 0; row < RANK_ROW_COUNT; row++) {
            ranks.add(new Rank(row, TOTAL_COL_COUNT));
        }
    }

    public List<Rank> getRanks() {
        return ranks;
    }
}
