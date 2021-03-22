package chess.domain.piece.attribute;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public enum Color {
    BLACK(String::toUpperCase),
    WHITE(String::toLowerCase);
    //BLANK(UnaryOperator.identity());

    private UnaryOperator<String> notationRule;

    Color(UnaryOperator<String> notationRule) {
        this.notationRule = notationRule;
    }

    public String changeNotation(String notation){
        return notationRule.apply(notation);
    }

    public Color opposite() {
        return Arrays.stream(values())
                .filter(color -> this != color)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("반대되는 색상이 없습니다."));
    }
}
