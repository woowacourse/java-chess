package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE;

    public boolean isSameColor(Color color) {
        return this == color;
    }

}
