package chess.domain;

import java.util.function.Function;

public enum Color {

    WHITE(String::toLowerCase),
    BLACK(String::toUpperCase),
    ;

    public final Function<String, String> caseConverter;

    Color(Function<String, String> caseConvertor) {
        this.caseConverter = caseConvertor;
    }

    public String convertToCase(String value) {
        return caseConverter.apply(value);
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
