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
        return (this.position.isDiagonalDifference(target, 1) || this.position.isHorizontalDifference(target, 1) || this.position.isVerticalDifference(target, 1));
    }
}
