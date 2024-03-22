package chess.domain.piece;

import java.util.function.Function;

public enum PieceNamePattern {
    WHITE(String::toLowerCase),
    BLACK(String::toUpperCase);

    private final Function<String, String> namingPattern;

    PieceNamePattern(Function<String, String> namingPattern) {
        this.namingPattern = namingPattern;
    }

    public String apply(String name) {
        return namingPattern.apply(name);
    }
}
