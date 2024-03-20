package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class Night extends Piece {

    public Night(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        if (this.position.isVerticalDifference(target, 2) && this.position.isHorizontalDifference(target, 1)) {
            return true;
        }
        if (this.position.isVerticalDifference(target, 1) && this.position.isHorizontalDifference(target, 2)) {
            return true;
        }
        return false;
    }
}
