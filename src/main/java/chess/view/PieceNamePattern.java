package chess.view;

import java.util.function.Function;

public enum PieceNamePattern {
    WHITE(String::toLowerCase),
    BLACK(String::toUpperCase);

    private final Function<String, String> namingPattern;

    PieceNamePattern(Function<String, String> namingPattern) {
        this.namingPattern = namingPattern;
    }

    public static String apply(PieceColorView color, String name) {
        if (color == PieceColorView.WHITE) {
            return WHITE.namingPattern.apply(name);
        }
        if (color == PieceColorView.BLACK) {
            return BLACK.namingPattern.apply(name);
        }
        throw new IllegalArgumentException("존재하지 않는 컬러입니다.");
    }
}
