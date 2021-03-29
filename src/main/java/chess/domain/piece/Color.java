package chess.domain.piece;

public enum Color {

    WHITE,
    BLACK;

    public Color reversed() {
        if(WHITE.equals(this)) {
            return BLACK;
        }
        return WHITE;
    }
}
