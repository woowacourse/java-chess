package chess.piece;

public enum PieceType {
    BISHOP("비숍"),
    KING("킹"),
    KNIGHT("나이트"),
    PAWN("폰"),
    QUEEN("퀸"),
    ROOK("룩");

    final String type;

    PieceType(String type) {
        this.type = type;
    }
}
