package chess.domain;

public class Piece {

    private final Color color;
    private final Type type;

    public Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public String getName() {
        if (color.equals(Color.WHITE)) {
            return type.getType().toLowerCase();
        }
        return type.getType();
    }
}
