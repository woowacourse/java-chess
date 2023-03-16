package domain.piece;

import domain.chessboard.SquareStatus;
import domain.coordinate.Position;
import domain.coordinate.Route;

public abstract class Piece implements SquareStatus {

    private final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract Route findRoute(final Position source, final Position target);
}
