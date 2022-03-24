package chess.domain.piece;

public enum Color {

    BLACK, WHITE, NONE;

    public Color oppositeColor() {
        if (this == NONE) {
            throw new IllegalStateException("[ERROR] 상대팀이 없습니다.");
        }
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
