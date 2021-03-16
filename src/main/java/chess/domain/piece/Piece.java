package chess.domain.piece;

public abstract class Piece {
    private final Color color;
    private final String notation;

    public Piece(Color color, String notation) {
        this.color = color;
        this.notation = notation;
    }

    public abstract boolean canMove();

    public String getNotation() {
        return color.changeNotation(notation);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", notation='" + notation + '\'' +
                '}';
    }
}
