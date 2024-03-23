package chess.domain.piece.type;

import chess.domain.Path;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    private static final int DEFAULT_STEP = 1;

    public King(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        Path path = Path.of(this.position, target);
        int rankDistance = this.position.getRankDistance(target);
        int fileDistance = this.position.getFileDistance(target);

        if (path == Path.VERTICAL && rankDistance == DEFAULT_STEP) {
            return true;
        }
        if (path == Path.HORIZONTAL && fileDistance == DEFAULT_STEP) {
            return true;
        }
        return (path == Path.LEFT_DIAGONAL || path == Path.RIGHT_DIAGONAL) && rankDistance == DEFAULT_STEP;
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        return new HashSet<>();
    }
}
