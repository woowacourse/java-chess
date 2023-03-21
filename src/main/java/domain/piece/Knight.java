package domain.piece;

import domain.piece.abstractpiece.JumperPiece;
import domain.position.Position;
import domain.type.PieceType;

public final class Knight extends JumperPiece {

    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int diffY = Math.abs(target.diffY(source));
        int diffX = Math.abs(target.diffX(source));

        return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
    }

}
