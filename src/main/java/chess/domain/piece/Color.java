package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE,
    EMPTY;

    public boolean isSameColor(Color color) {
        return this == color;
    }

}
