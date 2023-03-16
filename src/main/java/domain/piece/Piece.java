package domain.piece;

import domain.chessboard.SquareStatus;
import domain.coordinate.Position;
import domain.coordinate.Route;

public abstract class Piece implements SquareStatus {

    protected final Color color;

    protected PieceType pieceType;

    public Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public abstract Route findRoute(final Position source, final Position target);

    protected void validateMovable(final Position source, final Position target) {
        if (isMovable(source, target)) {
            return;
        }
        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    protected abstract boolean isMovable(final Position source, final Position target);


}
