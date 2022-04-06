package chess.domain;

import java.util.function.Function;

public enum Color {
    BLACK("black", String::toUpperCase),
    WHITE("white", String::toLowerCase),
    NONE("none", notation -> "empty");

    private final String value;
    private final Function<String, String> function;

    Color(String value, Function<String, String> function) {
        this.value = value;
        this.function = function;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public String getValue() {
        return value;
    }

    public String parse(String notation) {
        return function.apply(notation);
    }
}
