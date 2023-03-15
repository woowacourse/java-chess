package chess.domain;

public abstract class Piece {

    protected final Position position;
    protected final Color color;

    protected Piece(final File file, final Rank rank, final Color color) {
        this.color = color;
        this.position = new Position(file, rank);
    }

    public boolean isSameColor(final Color otherColor) {
        return this.color == otherColor;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }
}
