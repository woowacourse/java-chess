package chess.domain.piece;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Knight extends FixedMovePiece {

    public Knight(Team team) {
        super(PieceType.KNIGHT, team);
    }

    @Override
    Set<Entry<Integer, Integer>> weights() {
        return Set.of(
                Map.entry(-2, -1),
                Map.entry(-2, 1),
                Map.entry(-1, -2),
                Map.entry(-1, 2),
                Map.entry(1, -2),
                Map.entry(1, 2),
                Map.entry(2, -1),
                Map.entry(2, 1)
        );
    }
}
