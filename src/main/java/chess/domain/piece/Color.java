package chess.domain.piece;

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
}
