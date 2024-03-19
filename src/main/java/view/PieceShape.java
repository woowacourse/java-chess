package view;

import dto.PieceInfo;
import java.util.Arrays;

public enum PieceShape {
    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    BISHOP("B"),
    KNIGHT("N"),
    PAWN("P");

    private final String shape;

    PieceShape(String shape) {
        this.shape = shape;
    }

    public static PieceShape of(final String shape) {
        return Arrays.stream(PieceShape.values())
                .filter(pieceShape -> pieceShape.name().equals(shape))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 기물 모양입니다."));
    }

    public static String shapeValue(final PieceInfo pieceInfo) {
        if (pieceInfo.isWhite()) {
            return pieceInfo.shape().shape.toLowerCase();
        }
        return pieceInfo.shape().shape;
    }
}
