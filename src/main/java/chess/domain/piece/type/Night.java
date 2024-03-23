package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Night extends Piece {

    private static final int DEFAULT_STEP_ONE = 1;
    private static final int DEFAULT_STEP_TWO = 2;

    public Night(final Color color) {
        super(color);
    }

    @Override
    public boolean canMoveTo(final Position source, final Position target) {
        int rankDistance = source.getRankDistance(target);
        int fileDistance = source.getFileDistance(target);

        return (rankDistance == DEFAULT_STEP_TWO && fileDistance == DEFAULT_STEP_ONE)
                || (rankDistance == DEFAULT_STEP_ONE && fileDistance == DEFAULT_STEP_TWO);    }

    @Override
    public Set<Position> getRoute(final Position source, final Position target) {
        return new HashSet<>();
    }
}
