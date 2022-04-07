package chess.domain.piece;

import java.util.Arrays;

public enum Team {
    BLACK("black"), WHITE("white"), NONE("none");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isNone() {
        return this == NONE;
    }

    public String getTeamName() {
        return name;
    }

    public static Team of(String teamName) {
        return Arrays.stream(Team.values())
                .filter(team -> team.name.equalsIgnoreCase(teamName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 팀입니다."));
    }
}
