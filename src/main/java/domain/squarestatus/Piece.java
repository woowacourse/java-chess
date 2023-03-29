package domain.squarestatus;

import domain.position.Position;
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
    public final boolean isSameColor(final Color color) {
        return this.color.isSame(color);
    }

    @Override
    public final boolean isDifferentColor(final Color color) {
        return this.color.isDifferent(color);
    }

    @Override
    public final boolean isSameType(final Type type) {
        return this.pieceType.isSame(type);
    }

    @Override
    public final boolean isDifferentType(final Type type) {
        return this.pieceType.isDifferent(type);
    }

    protected final void validateMovable(final Position source, final Position target) {
        if (isMovable(source, target)) {
            return;
        }
        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    protected abstract boolean isMovable(final Position source, final Position target);

    @Override
    public final Type getType() {
        return pieceType;
    }

    @Override
    public final Color getColor() {
        return color;
    }

}
