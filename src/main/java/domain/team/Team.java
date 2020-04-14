package domain.team;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public enum Team {
    WHITE("white", String::toLowerCase),
    BLACK("black", String::toUpperCase),
    NONE("none", string -> string);

    private final String name;
    private final UnaryOperator<String> operator;

    Team(final String name, final UnaryOperator<String> operator) {
        this.name = name;
        this.operator = operator;
    }

    public static Team of(String teamName) {
        return Arrays.stream(values())
                .filter(team -> team.name.equals(teamName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String caseInitial(String initial) {
        return operator.apply(initial);
    }

    public String getName() {
        return name;
    }
}
