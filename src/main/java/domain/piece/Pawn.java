package domain.piece;

import domain.chessboard.Type;
import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.Collections;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        return new Route(Collections.emptyList());
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int direction = chooseDirection();

        int diffY = target.diffY(source);
        int diffX = target.diffX(source);

        return diffY == direction && (-1 <= diffX && diffX <= 1);
    }

    private int chooseDirection() {
        if (color == Color.WHITE) {
            return -1;
        }
        return 1;
    }

    @Override
    public Type getType() {
        return pieceType;
    }
}
