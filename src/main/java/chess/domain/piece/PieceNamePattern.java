package chess.domain.piece;

import java.util.function.Function;

public enum PieceNamePattern {
    WHITE(String::toLowerCase),
    BLACK(String::toUpperCase);

    private final Function<String, String> namingPattern;

    PieceNamePattern(Function<String, String> namingPattern) {
        this.namingPattern = namingPattern;
    }

    public static String apply(PieceColor color, String name) {
        if (color == PieceColor.WHITE) {
            return WHITE.namingPattern.apply(name);
        }
        if (color == PieceColor.BLACK) {
            return BLACK.namingPattern.apply(name);
        }
        throw new IllegalArgumentException("존재하지 않는 컬러입니다.");
    }
}
