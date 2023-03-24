package chess.domain.piece;

import java.util.Arrays;

public enum PieceType {
    ROOK("r", 5),
    KNIGHT("n", 2.5),
    BISHOP("b", 3),
    QUEEN("q", 9),
    KING("k", 0),
    PAWN("p", 1),
    EMPTY(".", 0);

    private final String name;
    private final double score;

    PieceType(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public static PieceType getTypeOf(String typeName) {
        return Arrays.stream(PieceType.values())
                .filter(type -> type.name.equals(typeName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 pieceType은 없습니다"));
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
