package chess.domain.piece;

import java.util.function.Function;

public enum Color {
    WHITE(String::toLowerCase),
    EMPTY(ignore -> {throw new IllegalStateException("빈 기물입니다.");}),
    BLACK(String::toUpperCase);

    private final Function<String, String> formatConverter;

    Color(Function<String, String> formatConverter) {
        this.formatConverter = formatConverter;
    }

    public String formatName(String name) {
        return this.formatConverter.apply(name);
    }

    public Color next() {
        if (this == Color.BLACK) return Color.WHITE;
        return Color.BLACK;
    }
}
