package chess.domain.piece;

import java.util.function.Function;

public enum Color {
    WHITE("백", String::toLowerCase),
    BLACK("흑", String::toUpperCase);

    private final String name;
    private final Function<String, String> formatConverter;

    Color(String name, Function<String, String> formatConverter) {
        this.name = name;
        this.formatConverter = formatConverter;
    }

    public String formatName(String name) {
        return this.formatConverter.apply(name);
    }

    public boolean isDifferent(Color other) {
        return this != other;
    }

    public boolean isEqual(Color other) {
        return this == other;
    }

    public Color getOppositeColor() {
        if (this == Color.WHITE) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public String getName() {
        return name;
    }
}
