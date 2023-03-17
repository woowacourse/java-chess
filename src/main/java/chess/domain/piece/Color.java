package chess.domain.piece;

import java.util.function.Function;

public enum Color {
    WHITE((name) -> name.toLowerCase()),
    BLACK((name) -> name.toUpperCase());

    private final Function<String, String> formatConverter;

    Color(Function<String, String> formatConverter) {
        this.formatConverter = formatConverter;
    }

    public String formatName(String name) {
        return this.formatConverter.apply(name);
    }
}
