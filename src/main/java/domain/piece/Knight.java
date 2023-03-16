package domain.piece;

import domain.chessboard.Type;
import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.Collections;

public final class Knight extends Piece {

    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);
        return new Route(Collections.emptyList());
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int diffY = Math.abs(target.diffY(source));
        int diffX = Math.abs(target.diffX(source));

        return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
    }

    @Override
    public Type getType() {
        return pieceType;
    }
}
