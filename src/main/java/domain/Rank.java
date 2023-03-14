package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {

    private final List<Piece> rank;

    public Rank(final List<Piece> rank) {
        this.rank = rank;
    }

    public static Rank initRankToRank(final InitRank initRank, final Color color) {
        return new Rank(InitRank.from(initRank).getTypes().stream()
                .map((type -> Piece.of(type, color)))
                .collect(Collectors.toList()));
    }

    public List<Piece> getRank() {
        return new ArrayList<>(rank);
    }

}
