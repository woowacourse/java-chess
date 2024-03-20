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
        if (this.position.isVerticalWithDistance(target, 2) && this.position.isHorizontalWithDistance(target, 1)) {
            return true;
        }
        return this.position.isVerticalWithDistance(target, 1) && this.position.isHorizontalWithDistance(target, 2);
    }
}
