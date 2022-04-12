package chess.domain.piece;

import java.util.Objects;

public enum PieceType {
    KING("k", 0), QUEEN("q", 9), ROOK("r", 5), KNIGHT("n", 2.5), BISHOP("b", 3), PAWN("p", 1), BLANK(".", 0);

    private final String signature;
    private final double score;

    PieceType(String signature, double score) {
        this.signature = signature;
        this.score = score;
    }

    public static PieceType of(String pieceType) {
        Objects.requireNonNull(pieceType, "존재하지 않는 기물 종류입니다.");

        try {
            return PieceType.valueOf(pieceType);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("해당하는 기물 종류를 찾을 수 없습니다.");
        }
    }

    public String getSignature() {
        return signature;
    }

    public double getScore() {
        return score;
    }
}
