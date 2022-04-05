package refactorChess.domain.piece;

import java.util.function.Function;

public enum PieceColor {

    WHITE(String::toLowerCase),
    BLACK(String::toUpperCase),
    NONE(Function.identity());

    private final Function<String, String> caseConverter;

    PieceColor(Function<String, String> caseConverter) {
        this.caseConverter = caseConverter;
    }

    public String convertToCase(String value) {
        return caseConverter.apply(value);
    }
}
