package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    NONE;

    public boolean isOpponent(Color color) {
        if(this == Color.BLACK && color == Color.WHITE) {
            return true;
        }
        return this == Color.WHITE && color == Color.BLACK;
    }

    public Color getOpponent() {
        if(this == Color.BLACK) {
            return Color.WHITE;
        }
        if(this == Color.WHITE) {
            return Color.BLACK;
        }
        throw new IllegalArgumentException("NONE은 반대되는 팀이 없습니다");
    }

    public boolean isSameColor(Color color) {
        if(this == color) {
            return true;
        }
        return false;
    }
}
