package domain.chess;

public enum Color {
    BLACK,
    WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public Color reverse() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
        };
    }

}
