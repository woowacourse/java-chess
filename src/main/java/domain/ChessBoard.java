package domain;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    public static final int RANK_SIZE = 8;
    private final List<Rank> ranks;

    public ChessBoard() {
        ranks = new ArrayList<>();
        for (int index = 0; index < RANK_SIZE; index++) {
            ranks.add(new Rank(new RankGenerator(), index));
        }
    }

    public int size() {
        return ranks.size();
    }
}
