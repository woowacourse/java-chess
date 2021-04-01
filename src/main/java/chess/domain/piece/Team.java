package chess.domain.piece;

import java.util.Arrays;

public enum Team {
    BLACK("black"),
    WHITE("white"),
    NOTHING("");

    private final String teamName;

    Team(final String teamName) {
        this.teamName = teamName;
    }

    public static Team matchingTeam(String teamName) {
        return Arrays.stream(values())
                .filter(team -> team.teamName.equals(teamName))
                .findFirst()
                .orElseThrow(() ->  new IllegalArgumentException("일치하는 팀이 없습니다."));
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
