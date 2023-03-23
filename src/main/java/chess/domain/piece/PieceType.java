package chess.domain.piece;

import java.util.Arrays;

public enum PieceType {

    PAWN(1),
    KING(0),
    QUEEN(9),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5);

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public static PieceType findByName(final String name) {
        return Arrays.stream(values())
            .filter(type -> type.name().equalsIgnoreCase(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다."));
    }

}
