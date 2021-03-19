package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Division extends Basis {
    protected final Color color;
    protected Position position;

    public Division(Color color, String displayName, Position position) {
        super(displayName);
        this.color = color;
        this.position = position;
    }

    public boolean isBlack() {
        return Color.BLACK.equals(color);
    }

    public boolean isWhite() {
        return Color.WHITE.equals(color);
    }

    public abstract void move(Position to, Pieces pieces);

    public abstract void kill(Position to, Pieces pieces);

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String display() {
        if (isBlack()) {
            return super.display()
                        .toUpperCase();
        }
        return super.display();
    }

    @Override
    public boolean hasPosition(Position position) {
        return this.position.equals(position);
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
