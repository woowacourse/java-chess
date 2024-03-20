package view;

import domain.piece.Piece;
import dto.RankInfo;
import java.util.Arrays;
import java.util.List;

public enum PieceShape {
    KING("K", "k"),
    QUEEN("Q", "q"),
    ROOK("R", "r"),
    BISHOP("B", "b"),
    KNIGHT("N", "n"),
    PAWN("P", "p"),
    NONE(".", ".");

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
                .orElseThrow(() -> new IllegalArgumentException("잘못된 기물 모양입니다."));
    }


    public static String whiteShapeOf(final String type) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(type))
                .map(value -> value.whiteShape)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 기물 모양입니다."));

    }

    public static String blackShapeOf(final String type) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(type))
                .map(value -> value.blackShape)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 기물 모양입니다."));
    }
}
