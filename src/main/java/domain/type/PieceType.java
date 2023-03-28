package domain.type;

import java.util.Arrays;

public enum PieceType {
    ROOK(5),
    KNIGHT(2.5),
    BISHOP(3),
    QUEEN(9),
    KING(0),
    PAWN(1),
    EMPTY(0);

    private final double score;

    PieceType(final double score) {
        this.score = score;
    }

    public static PieceType findByName(final String name) {
        return Arrays.stream(PieceType.values())
            .filter(pieceType -> pieceType.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("적절하지 않은 기물 타입입니다."));
    }

    public double getScore() {
        return score;
    }
}
