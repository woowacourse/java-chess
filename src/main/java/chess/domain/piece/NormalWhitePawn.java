package chess.domain.piece;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class NormalWhitePawn extends Pawn {
    public NormalWhitePawn() {
        super(Team.WHITE);
    }

    @Override
    Set<Entry<Integer, Integer>> straightWeights() {
        return Set.of(Map.entry(0, 1));
    }

    @Override
    Set<Entry<Integer, Integer>> diagonalWeights() {
        return Set.of(
                Map.entry(1, 1),
                Map.entry(-1, 1)
        );
    }
}
