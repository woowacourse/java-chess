package chess.domain;

public enum Color {

    BLACK,
    WHITE,
    BLANK;

    public Color getOppositeColor() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        if (this.equals(BLACK)) {
            return WHITE;
        }
        throw new IllegalArgumentException("[ERROR] BLANK는 반대 진영이 존재하지 않습니다.");
    }
}
