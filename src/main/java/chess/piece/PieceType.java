package chess.piece;

public enum PieceType {
    BISHOP("숍"),
    KING("킹"),
    KNIGHT("말"),
    PAWN("폰"),
    QUEEN("퀸"),
    ROOK("룩");

    final String type;

    PieceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
