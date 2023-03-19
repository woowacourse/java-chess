package domain.squarestatus;

import domain.coordinate.Position;
import domain.piece.Color;
import domain.type.PieceType;
import domain.type.Type;

public abstract class Piece implements SquareStatus {

    protected final Color color;
    protected final PieceType pieceType;

    public Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    @Override
    public final Type getType() {
        return pieceType;
    }

    @Override
    public final Color getColor() {
        return color;
    }

    protected final void validateMovable(final Position source, final Position target) {
        if (isMovable(source, target)) {
            return;
        }
        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    protected abstract boolean isMovable(final Position source, final Position target);

}
