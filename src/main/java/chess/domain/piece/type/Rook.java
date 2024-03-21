package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        return this.position.isStraightWith(target);
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        if (this.position.isVerticalWith(target)) {
            return this.position.getVerticalMiddlePositions(target);
        }

        if (this.position.isHorizontalWith(target)) {
            return this.position.getHorizontalMiddlePositions(target);
        }

        return new HashSet<>();
    }
}
