package domain.piece;

import domain.chessboard.SquareStatus;
import domain.coordinate.Position;
import domain.coordinate.Route;

public abstract class Piece implements SquareStatus {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract Route findRoute(final Position source, final Position target);

    protected void validateMovable(final Position source, final Position target) {
        if (isMovable(source, target)) {
            return;
        }
        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    protected abstract boolean isMovable(final Position source, final Position target);


}
