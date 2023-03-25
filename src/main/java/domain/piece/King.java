package domain.piece;

import domain.piece.abstractpiece.JumperPiece;
import domain.position.Position;
import domain.type.PieceType;

public final class King extends JumperPiece {

    private static final int STAY = 0;
    private static final int MAX_DIFF = 2;

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int diffY = Math.abs(target.diffY(source));
        int diffX = Math.abs(target.diffX(source));

        return (diffY != STAY || diffX != STAY) && (diffX < MAX_DIFF && diffY < MAX_DIFF);
    }

}
