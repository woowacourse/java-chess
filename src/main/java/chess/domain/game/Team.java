package chess.domain.game;

import java.util.Arrays;

public enum Team {
    BLACK("Black Team"),
    WHITE("White Team");

    private String teamName;

    Team(String teamName) {
        this.teamName = teamName;
    }

    public static Team getOpponentTeam(Team team) {
        if (team == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public static Team of(String teamName) {
        return Arrays.stream(values())
                .filter(o -> o.teamName.equals(teamName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public String getTeamName() {
        return teamName;
    }
}
