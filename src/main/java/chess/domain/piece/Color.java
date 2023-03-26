package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    NONE;

    public static boolean isSameColor(Color color, Color compareColor){
        return color.equals(compareColor);
    }
}
