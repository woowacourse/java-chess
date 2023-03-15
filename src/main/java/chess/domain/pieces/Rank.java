package chess.domain.pieces;

import chess.domain.board.Square;
import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final List<Square> rank;

    public Rank(final List<Square> rank) {
        this.rank = new ArrayList<>(rank);
    }

    public List<Square> getRank() {
        return List.copyOf(rank);
    }
}
