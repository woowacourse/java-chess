package domain.team;

import java.util.function.UnaryOperator;

public enum Team {
    BLACK(String::toUpperCase),
    WHITE(String::toLowerCase),
    NONE(string -> ".");

    private UnaryOperator<String> operator;

    Team(UnaryOperator<String> operator) {
        this.operator = operator;
    }

    public String caseInitial(String initial) {
        return operator.apply(initial);
    }
}
