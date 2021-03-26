package chess.domain.piece;

public enum PieceColor {

    WHITE,
    BLACK;

    public PieceColor reversed() {
        if(WHITE.equals(this)) {
            return BLACK;
        }
        return WHITE;
    }
}
