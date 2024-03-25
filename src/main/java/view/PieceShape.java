package view;

import java.util.Arrays;

public enum PieceShape {
    KING("K", "k"),
    QUEEN("Q", "q"),
    ROOK("R", "r"),
    BISHOP("B", "b"),
    KNIGHT("N", "n"),
    PAWN("P", "p"),
    EMPTY(".", ".");

    public static final String PIECE_SHAPE_NOT_FOUND = "찾으려는 모양이 존재하지 않습니다.";
    private final String blackShape;
    private final String whiteShape;

    PieceShape(final String blackShape, final String whiteShape) {
        this.blackShape = blackShape;
        this.whiteShape = whiteShape;
    }

    public static String whiteShapeOf(final String shape) {
        return Arrays.stream(values())
                .filter(value -> shape.toLowerCase().contains(value.name().toLowerCase()))
                .map(value -> value.whiteShape)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PIECE_SHAPE_NOT_FOUND));

    }

    public static String blackShapeOf(final String shape) {
        return Arrays.stream(values())
                .filter(value -> shape.toLowerCase().contains(value.name().toLowerCase()))
                .map(value -> value.blackShape)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PIECE_SHAPE_NOT_FOUND));
    }
}
