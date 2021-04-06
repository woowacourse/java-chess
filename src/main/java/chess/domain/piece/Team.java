package chess.domain.piece;

import java.util.Arrays;

public enum Team {
    BLACK("Black"),
    WHITE("White"),
    NOTHING("");

    public static final int PLAYER_NUMBER = 2;
    private final String teamName;

    Team(final String teamName) {
        this.teamName = teamName;
    }

    public Team anyTeamExcludingThis() {
        return Arrays.stream(values())
            .filter(team -> team != this)
            .findAny()
            .orElse(NOTHING);
    }

    public String teamName() {
        return this.teamName;
    }

    public String oppositeTeamName() {
        return this.oppositeTeam().teamName;
    }

    public boolean isOppositeTeam(final Team team) {
        return this.oppositeTeam() == team;
    }

    public boolean isBlackTeam() {
        return this == BLACK;
    }

    public boolean isSameTeam(final Team team) {
        return this == team;
    }

    public Team oppositeTeam() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return this;
    }

    public boolean undefined() {
        return this == NOTHING;
    }
}
