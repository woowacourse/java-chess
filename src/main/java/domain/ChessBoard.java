package domain;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    public static final int RANK_SIZE = 8;
    private final List<Rank> ranks;

    public ChessBoard() {
        ranks = new ArrayList<>();
        for (int row = 0; row < RANK_SIZE; row++) {
            ranks.add(new Rank(new RankGenerator(), row));
        }
    }

    public int size() {
        return ranks.size();
    }

    public List<Rank> getRanks() {
        return ranks;
    }
}
