package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

public abstract class Division extends Basis {
    protected final Color color;
    protected Position position;

    public Division(final Color color, final String displayName, final Position position) {
        super(displayName);
        this.color = color;
        this.position = position;
    }

    protected Row initPawnRow() {
        return color.initPawnRow();
    }

    public abstract void moveToEmpty(final Position to, final Pieces pieces);

    public abstract void moveForKill(final Position to, final Pieces pieces);

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String display() {
        if (Color.BLACK.equals(color)) {
            return super.display()
                    .toUpperCase();
        }
        return super.display();
    }

    @Override
    public boolean hasPosition(final Position position) {
        return this.position.equals(position);
    }

    @Override
    public boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public abstract boolean isKing();

    public abstract double score();

    public abstract boolean isPawn();

    @Override
    public Column getColumn() {
        return position.column();
    }
}
