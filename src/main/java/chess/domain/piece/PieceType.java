package chess.domain.piece;

import java.util.Arrays;

public enum PieceType {

    BISHOP("bishop", "b", 3),
    KING("king", "k", 0),
    ROOK("rook", "r", 5),
    KNIGHT("knight", "n", 2.5),
    QUEEN("queen", "q", 9),
    PAWN("pawn", "p", 1);

    private final String name;
    private final String signature;
    private final double score;

    PieceType(String name, String signature, double score) {
        this.name = name;
        this.signature = signature;
        this.score = score;
    }

    public static PieceType find(String name) {
        return Arrays.stream(values())
                .filter(type -> name.equals(type.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 타입을 찾을 수 없습니다."));
    }

    public String getSignature() {
        return signature;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
