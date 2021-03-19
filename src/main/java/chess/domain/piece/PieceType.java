package chess.domain.piece;

public enum PieceType {

    EMPTY("."),
    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k");

    private String type;

    PieceType(String type) {
        this.type = type;
    }

    public String toBlack() {
        if (this.equals(EMPTY)) {
            throw new IllegalArgumentException("빈 칸은 변환할 수 없습니다.");
        }
        return type.toUpperCase();
    }

    public String getType() {
        return type;
    }
}
