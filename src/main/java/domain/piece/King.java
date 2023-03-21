package domain.piece;

import domain.coordinate.Position;
import domain.type.PieceType;

public final class King extends JumperPiece {

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int diffY = Math.abs(target.diffY(source));
        int diffX = Math.abs(target.diffX(source));

        return (diffY != 0 || diffX != 0) && (diffX < 2 && diffY < 2);
    }

}
