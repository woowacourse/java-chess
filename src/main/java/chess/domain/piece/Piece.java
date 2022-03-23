package chess.domain.piece;

public abstract class Piece {

    private final Color color;
    private final String name;

    protected Piece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public final boolean isEqualColor(Piece piece) {
        return this.color == piece.color;
    }

    public final String convertedName() {
        return color.convertToCase(name);
    }
}
