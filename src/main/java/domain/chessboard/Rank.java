package domain.chessboard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {

    private final List<Square> rank;

    public Rank(final List<Square> rank) {
        this.rank = rank;
    }

    public static Rank initRankToRank(final RankFactory rankFactory) {
        return new Rank(rankFactory.getSquareStatus()
                .stream()
                .map(Square::new)
                .collect(Collectors.toList()));
    }

    public List<Square> getRank() {
        return new ArrayList<>(rank);
    }

}
