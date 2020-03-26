package chess.domain.piece;

import java.util.function.Function;

public enum Team {
    BLACK(String::toUpperCase),
    WHITE(String::toLowerCase);

    private final Function<String, String> expression;

    Team(Function<String, String> expression) {
        this.expression = expression;
    }

    public static Team changeTurn(Team team) {
        if (team == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String convert(final String name) {
        return this.expression.apply(name);
    }

    public boolean isEnemy(Team team) {
        return this != team;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
