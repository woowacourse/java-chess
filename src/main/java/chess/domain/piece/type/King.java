package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    public King(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        return (this.position.isDiagonalWithDistance(target, 1) || this.position.isHorizontalWithDistance(target, 1) || this.position.isVerticalWithDistance(target, 1));
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        return new HashSet<>();
    }
}
