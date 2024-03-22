package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Night extends Piece {

    private static final int DEFAULT_STEP_ONE = 1;
    private static final int DEFAULT_STEP_TWO = 2;

    public Night(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        return (this.position.getRankDistance(target) == DEFAULT_STEP_TWO && this.position.getFileDistance(target) == DEFAULT_STEP_ONE)
                || (this.position.getRankDistance(target) == DEFAULT_STEP_ONE && this.position.getFileDistance(target) == DEFAULT_STEP_TWO);
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        return new HashSet<>();
    }
}
