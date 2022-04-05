package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    ;

    public static Color nameOf(String input) {
        if (BLACK.name().equals(input.toUpperCase())) {
            return BLACK;
        }
        return WHITE;
    }
}
