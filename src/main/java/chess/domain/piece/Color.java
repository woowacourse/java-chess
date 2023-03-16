package chess.domain.piece;

public enum Color { // TODO: 이름바꾸기
    BLACK,
    WHITE;

    public static Color change(Color color) {
        if (color == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
