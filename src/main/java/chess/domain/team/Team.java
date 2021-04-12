package chess.domain.team;

import java.util.Arrays;

public enum Team {
    WHITE("white"),
    BLACK("black");

    private final String value;

    Team(final String value) {
        this.value = value;
    }

    public static Team from(final String value) {
        return Arrays.stream(values())
            .filter(team -> team.value.equals(value.toLowerCase()))
            .findAny()
            .orElseThrow(() -> new TeamNotFoundException(value));
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public Team reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public String getValue() {
        return value;
    }
}
