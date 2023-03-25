package domain.piece;

import domain.piece.abstractpiece.JumperPiece;
import domain.position.Position;
import domain.type.PieceType;

public final class Knight extends JumperPiece {

    private static final int MIN_DIFF = 1;
    private static final int MAX_DIFF = 2;

    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int diffY = Math.abs(target.diffY(source));
        int diffX = Math.abs(target.diffX(source));

        return (diffX == MIN_DIFF && diffY == MAX_DIFF) || (diffX == MAX_DIFF && diffY == MIN_DIFF);
    }

}
