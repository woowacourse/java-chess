package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Set;

public class Pawn extends Piece {

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    private boolean isInitPosition() {
        if (color.equals(Color.WHITE)) {
            return this.position.isTwoRank();
        }
        return this.position.isSevenRank();
    }

    @Override
    public boolean canMoveTo(final Position target) {
        if (color.equals(Color.WHITE)) {
            if (isInitPosition()) {
                return this.position.isForwardWithDistance(target, 2) || this.position.isForwardWithDistance(target, 1);
            }
            return this.position.isForwardWithDistance(target, 1);
        }

        if (isInitPosition()) {
            return this.position.isForwardWithDistance(target, -2) || this.position.isForwardWithDistance(target, -1);
        }
        return this.position.isForwardWithDistance(target, -1);
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        return this.position.getVerticalMiddlePositions(target);
    }
}
