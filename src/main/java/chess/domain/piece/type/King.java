package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class King extends Piece {

    public King(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        return (this.position.isDiagonalWithDistance(target, 1) || this.position.isHorizontalWithDistance(target, 1) || this.position.isVerticalWithDistance(target, 1));
    }
}
