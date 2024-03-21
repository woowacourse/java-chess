package view;

import java.util.Arrays;

public enum PieceShape {
    KING("K", "k"),
    QUEEN("Q", "q"),
    ROOK("R", "r"),
    BISHOP("B", "b"),
    KNIGHT("N", "n"),
    PAWN("P", "p"),
    NONE(".", ".");

    public static final String PIECE_SHAPE_NOT_FOUND = "잘못된 기물 모양입니다.";
    private final String blackShape;
    private final String whiteShape;

    PieceShape(final String blackShape, final String whiteShape) {
        this.blackShape = blackShape;
        this.whiteShape = whiteShape;
    }

    public static PieceShape of(final String shape) {
        return Arrays.stream(PieceShape.values())
                .filter(pieceShape -> pieceShape.name().equals(shape))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PIECE_SHAPE_NOT_FOUND));
    }


    public static String whiteShapeOf(final String shape) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(shape))
                .map(value -> value.whiteShape)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PIECE_SHAPE_NOT_FOUND));

    }

    public static String blackShapeOf(final String shape) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(shape))
                .map(value -> value.blackShape)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PIECE_SHAPE_NOT_FOUND));
    }
}
