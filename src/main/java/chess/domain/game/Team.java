package chess.domain.game;

import java.util.Arrays;

public enum Team {
    BLACK("Black Team"),
    WHITE("White Team");

    private final String teamName;

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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
    }

    public boolean isSameTeamName(String compareTeam) {
        return teamName.equals(compareTeam);
    }

    public String getTeamName() {
        return teamName;
    }
}
