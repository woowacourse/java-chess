package techcourse.fp.chess.domain.piece;

import java.util.Arrays;

public enum PieceType {
    PAWN(1),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(0),
    EMPTY(0);

    private final double score;

    PieceType(final double score) {
        this.score = score;
    }

    public static PieceType createByName(String name) {
            return Arrays.stream(values())
                    .filter(pieceType -> pieceType.name().equalsIgnoreCase(name))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 pieceType을 입력하셨습니다."));
    }

    public double getScore() {
        return score;
    }
}
