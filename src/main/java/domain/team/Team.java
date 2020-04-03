package domain.team;

import java.util.function.UnaryOperator;

public enum Team {
    WHITE(String::toLowerCase),
    BLACK(String::toUpperCase),
    NONE(string -> string);

    private UnaryOperator<String> operator;

    Team(UnaryOperator<String> operator) {
        this.operator = operator;
    }

    public String caseInitial(String initial) {
        return operator.apply(initial);
    }
}
