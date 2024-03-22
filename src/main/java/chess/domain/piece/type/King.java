package chess.domain.piece.type;

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
        return (this.position.isVerticalWith(target) && this.position.getRankDistance(target) == DEFAULT_STEP)
                || (this.position.isHorizontalWith(target) && this.position.getFileDistance(target) == DEFAULT_STEP)
                || (this.position.isDiagonalWith(target) && this.position.getRankDistance(target) == DEFAULT_STEP);
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        return new HashSet<>();
    }
}
