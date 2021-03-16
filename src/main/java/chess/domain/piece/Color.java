package chess.domain.piece;

import java.util.function.UnaryOperator;

public enum Color {
    BLACK(String::toUpperCase),
    WHITE(String::toLowerCase);

    private UnaryOperator<String> notationRule;

    Color(UnaryOperator<String> notationRule) {
        this.notationRule = notationRule;
    }

    public String changeNotation(String notation){
        return notationRule.apply(notation);
    }
}
